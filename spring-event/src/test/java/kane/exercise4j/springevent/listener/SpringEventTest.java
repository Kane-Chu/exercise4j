package kane.exercise4j.springevent.listener;

import java.util.UUID;

import kane.exercise4j.springevent.SpringEventApplication;
import kane.exercise4j.springevent.bean.User;
import kane.exercise4j.springevent.event.UserLoginEvent;
import kane.exercise4j.springevent.event.UserRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringEventApplication.class)
public class SpringEventTest {


    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void fireLogin() {
        User user = User.builder().uid(UUID.randomUUID().toString()).username("kane").build();
        applicationContext.publishEvent(new UserLoginEvent(user));
    }

    @Test
    public void fireRegister() {
        User user = User.builder().uid(UUID.randomUUID().toString()).username("kane").build();
        applicationContext.publishEvent(new UserRegisterEvent(user));
    }



}
