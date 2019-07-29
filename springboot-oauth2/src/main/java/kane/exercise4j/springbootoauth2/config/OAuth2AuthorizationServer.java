package kane.exercise4j.springbootoauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author kane
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("kane-app")
                // 这里的{noop}必须加，表示实用明文密码模式，不进行加密（此为spring security5.0新加限制 参考DelegatingPasswordEncoder）
                .secret("{noop}pass1234")
                .redirectUris("http://localhost:9090/callback")
                .authorizedGrantTypes("refresh_token", "authorization_code")
                .scopes("read_user_info", "read_contacts")
                // 用于验证refresh_token 将access_token的有效期设置短一点
                .accessTokenValiditySeconds(10);
    }
}