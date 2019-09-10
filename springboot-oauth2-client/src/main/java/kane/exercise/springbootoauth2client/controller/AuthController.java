package kane.exercise.springbootoauth2client.controller;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import kane.exercise.springbootoauth2client.properties.OAuth2Properties;
import kane.exercise.springbootoauth2client.properties.WebProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * @author kane
 */
@Slf4j
@Controller
public class AuthController {

    private final OAuth2Properties oAuth2Properties;
    private final WebProperties webProperties;

    private String accessToken;
    private String refreshToken;

    public AuthController(OAuth2Properties oAuth2Properties, WebProperties webProperties) {
        this.oAuth2Properties = oAuth2Properties;
        this.webProperties = webProperties;
    }

    @RequestMapping("/oauth2")
    public String oauth2() {
        String uri = oAuth2Properties.getAuthUri();
        log.debug("redirect to [{}]", uri);
        return "redirect:" + uri;
    }

    @RequestMapping("/callback")
    @ResponseBody
    public Map callback(@RequestParam String code) {
        log.debug("callback code[{}]", code);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(oAuth2Properties.getClientId(), oAuth2Properties.getClientSecret(), StandardCharsets.UTF_8);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", code);
        body.add("grant_type", "authorization_code");
        body.add("redirect_uri", "http://" + webProperties.getDomain() + oAuth2Properties.getCallback());
        body.add("scope", "read_user_info");

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = new RestTemplate().exchange(oAuth2Properties.getTokenUri(), HttpMethod.POST, httpEntity, Map.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            Map responseBody = response.getBody();
            if (responseBody == null) {
                log.warn("response null");
                return null;
            }
            accessToken = responseBody.get("access_token").toString();
            refreshToken = responseBody.get("refresh_token").toString();
            log.debug("access_token [{}] refresh_token[{}]", accessToken, refreshToken);
            return responseBody;
        }
        log.warn("response code [{}], not success!", response.getStatusCode());
        return null;
    }

    @GetMapping("/api/users/current")
    @ResponseBody
    public String callback() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response;
        try {
            response = new RestTemplate().exchange(oAuth2Properties.getResourceUri(), HttpMethod.GET, httpEntity, String.class);
        } catch (HttpClientErrorException e) {
            refreshToken();
            headers.setBearerAuth(accessToken);
            httpEntity = new HttpEntity<>(headers);
            response = new RestTemplate().exchange(oAuth2Properties.getResourceUri(), HttpMethod.GET, httpEntity, String.class);
        }
        return response.getBody();
    }


    private void refreshToken() {
        log.debug("refresh token [{}]", refreshToken);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(oAuth2Properties.getClientId(), oAuth2Properties.getClientSecret(), StandardCharsets.UTF_8);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "refresh_token");
        body.add("refresh_token", refreshToken);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response;
        try {
            response = new RestTemplate().exchange(oAuth2Properties.getTokenUri(), HttpMethod.POST, httpEntity, Map.class);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("需要重新授权");
        }

        Map responseBody = response.getBody();

        if (responseBody == null) {
            throw new RuntimeException("获取到的token为空");
        }

        accessToken = responseBody.get("access_token").toString();
        refreshToken = responseBody.get("refresh_token").toString();
        log.debug("access_token [{}] refresh_token[{}]", accessToken, refreshToken);
    }

}