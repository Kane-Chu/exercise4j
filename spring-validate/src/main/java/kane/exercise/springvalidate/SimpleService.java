package kane.exercise.springvalidate;

import javax.validation.Valid;

import kane.exercise.springvalidate.bean.User;
import org.springframework.validation.annotation.Validated;

/**
 * @author kane
 */
public interface SimpleService {
    void saveUser(@Valid User user);
}