package kane.exercise.springbootoauth2client.properties;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

/**
 * @author kane
 */
@Data
@ConfigurationProperties("web")
public class WebProperties implements InitializingBean {
    private String domain;

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(domain, "Must specify {web.domain}");
    }
}