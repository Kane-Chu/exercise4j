package kane.exercise.springvalidate;

import javax.validation.Valid;

import kane.exercise.springvalidate.bean.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * @author kane
 */
@Service
@Validated
public class SimpleServiceImpl implements SimpleService {
    @Override
    public void saveUser(User user) {
        System.out.println("hello " + user);
    }
}