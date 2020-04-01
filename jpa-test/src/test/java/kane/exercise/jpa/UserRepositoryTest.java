package kane.exercise.jpa;

import java.util.List;

import kane.exercise.jpa.entity.User;
import kane.exercise.jpa.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author kane
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaTestApplication.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private static List<User> userData = Lists.newArrayList(
            User.builder().id(1L).name("Tom").build(),
            User.builder().id(2L).name("Jerry").build()
    );


    @Before
    public void init() {
        for (User user : userData) {
            userRepository.save(user);
        }
    }

    @Test
    public void findUserByIds() {
        List<User> users = userRepository.findUserByIds(Lists.newArrayList(3L));
        log.info("users [{}]", users);
    }

    @After
    public void destroy() {
        for (User user : userData) {
            userRepository.deleteById(user.getId());
        }
    }
}