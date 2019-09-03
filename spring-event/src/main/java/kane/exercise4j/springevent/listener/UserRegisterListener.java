package kane.exercise4j.springevent.listener;

import kane.exercise4j.springevent.bean.User;
import kane.exercise4j.springevent.event.UserLoginEvent;
import kane.exercise4j.springevent.event.UserRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author kane
 */
@Slf4j
@Component
public class UserRegisterListener implements ApplicationListener<UserRegisterEvent> {
    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        log.info("listening in UserRegisterListener ...");
        User user = (User) event.getSource();
        log.info("user[{}] register ...", user.getUid());
    }
}