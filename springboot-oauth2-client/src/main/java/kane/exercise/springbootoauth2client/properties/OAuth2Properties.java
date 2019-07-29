package kane.exercise.springbootoauth2client.properties;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

/**
 * @author kane
 */
@Data
@ConfigurationProperties("web.oauth2")
public class OAuth2Properties implements InitializingBean {
    private String authUri;
    private String tokenUri;
    private String resourceUri;
    private String clientId;
    private String clientSecret;
    private String callback;

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(authUri, "Must specify {web.oauth2.auth-uri}");
        Assert.notNull(tokenUri, "Must specify {web.oauth2.token-uri}");
        Assert.notNull(resourceUri, "Must specify {web.oauth2.resource-uri}");
        Assert.notNull(clientId, "Must specify {web.oauth2.client-id}");
        Assert.notNull(clientSecret, "Must specify {web.oauth2.client-secret}");
        Assert.notNull(callback, "Must specify {web.oauth2.callback}");
    }
}