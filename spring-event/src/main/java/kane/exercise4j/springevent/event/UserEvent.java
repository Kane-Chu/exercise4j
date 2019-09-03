package kane.exercise4j.springevent.event;

import kane.exercise4j.springevent.bean.User;
import org.springframework.context.ApplicationEvent;

/**
 * @author kane
 */
public class UserEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param user 用户对象
     */
    public UserEvent(User user) {
        super(user);
    }
}