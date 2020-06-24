package kane.exercise.jooq;

import kane.exercise.jooq.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {@link kane.exercise.jooq.service.UserService} 测试类
 *
 * @author kane
 */
@SpringBootTest(classes = JooqApplication.class)
@RunWith(SpringRunner.class)
public class UserServiceTest {
    private final UserService userService;


    public UserServiceTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void queryTest(){

    }
}