package kane.exercise.springvalidate;


import kane.exercise.springvalidate.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringValidateApplication.class)
public class SimpleServiceImplTest {

    @Autowired
    private SimpleService simpleService;

    @Test
    public void test() {
        User user = User.builder().name("cjm").build();
        simpleService.saveUser(user);
    }

}
