package kane.exercise.springbootoauth2client.config;

import java.util.Collections;

import kane.exercise.springbootoauth2client.properties.OAuth2Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

/**
 * @author kane
 */
@Configuration
public class OAuth2Config {

    private final OAuth2Properties oAuth2Properties;

    private final ClientHttpRequestFactory clientHttpRequestFactory;

    public OAuth2Config(OAuth2Properties oAuth2Properties, @Autowired(required = false) ClientHttpRequestFactory clientHttpRequestFactory) {
        this.oAuth2Properties = oAuth2Properties;
        if(clientHttpRequestFactory == null){
            this.clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        }else{
            this.clientHttpRequestFactory = clientHttpRequestFactory;
        }

    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        return clientHttpRequestFactory;
    }

    @Bean
    @Qualifier("myRestTemplate")
    public OAuth2RestOperations restTemplate() {

        OAuth2RestTemplate template = new OAuth2RestTemplate(fullAccessResourceDetails(), new DefaultOAuth2ClientContext(
                new DefaultAccessTokenRequest()));
        return prepareTemplate(template, false);
    }

    @Bean
    @Qualifier("myClientOnlyRestTemplate")
    public OAuth2RestOperations restClientOnlyTemplate() {

        OAuth2RestTemplate template = new OAuth2RestTemplate(fullAccessResourceDetailsClientOnly(), new DefaultOAuth2ClientContext(
                new DefaultAccessTokenRequest()));
        return prepareTemplate(template, true);
    }

    public OAuth2RestTemplate prepareTemplate(OAuth2RestTemplate template, boolean isClient) {
        template.setRequestFactory(getClientHttpRequestFactory());
        if (isClient) {
            template.setAccessTokenProvider(clientAccessTokenProvider());
        } else {
            template.setAccessTokenProvider(userAccessTokenProvider());
        }
        return template;
    }

    @Bean
    public AccessTokenProvider userAccessTokenProvider() {
        ResourceOwnerPasswordAccessTokenProvider accessTokenProvider = new ResourceOwnerPasswordAccessTokenProvider();
        accessTokenProvider.setRequestFactory(getClientHttpRequestFactory());
        return accessTokenProvider;
    }

    @Bean
    public AccessTokenProvider clientAccessTokenProvider() {
        ClientCredentialsAccessTokenProvider accessTokenProvider = new ClientCredentialsAccessTokenProvider();
        accessTokenProvider.setRequestFactory(getClientHttpRequestFactory());
        return accessTokenProvider;
    }

    @Bean
    @Qualifier("myFullAccessDetails")
    public OAuth2ProtectedResourceDetails fullAccessResourceDetails() {
        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
        resource.setAccessTokenUri(oAuth2Properties.getTokenUri());
        resource.setClientId("user_member");
        resource.setGrantType("password");
        resource.setScope(Collections.singletonList("read_user_info"));
        resource.setUsername("roy");
        resource.setPassword("spring");
        return resource;
    }

    @Bean
    @Qualifier("myClientOnlyFullAccessDetails")
    public OAuth2ProtectedResourceDetails fullAccessResourceDetailsClientOnly() {
        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
        resource.setAccessTokenUri(oAuth2Properties.getTokenUri());
        resource.setClientId("clientapp");
        resource.setClientSecret("123456");
        resource.setGrantType("client_credentials");
        resource.setScope(Collections.singletonList("read_user_info"));;
        return resource;
    }
}