package kane.exercise4j.springevent.listener;

import kane.exercise4j.springevent.bean.User;
import kane.exercise4j.springevent.event.UserLoginEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author kane
 */
@Slf4j
@Component
public class UserLoginListener implements ApplicationListener<UserLoginEvent> {
    @Override
    public void onApplicationEvent(UserLoginEvent event) {
        log.info("listening in UserLoginListener ...");
        User user = (User) event.getSource();
        log.info("user[{}] login ...", user.getUid());
    }
}