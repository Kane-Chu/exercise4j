package kane.exercise.springbootoauth2client;

import kane.exercise.springbootoauth2client.properties.OAuth2Properties;
import kane.exercise.springbootoauth2client.properties.WebProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author kane
 */
@SpringBootApplication
@EnableConfigurationProperties({WebProperties.class, OAuth2Properties.class})
public class OAuth2ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAuth2ClientApplication.class, args);
    }
}