package kane.exercise.springvalidate;

import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * @author kane
 */
@SpringBootApplication
public class SpringValidateApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringValidateApplication.class, args);
    }
}