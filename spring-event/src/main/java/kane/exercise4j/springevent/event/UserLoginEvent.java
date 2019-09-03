package kane.exercise4j.springevent.event;

import kane.exercise4j.springevent.bean.User;

/**
 * @author kane
 */
public class UserLoginEvent extends UserEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param user 登陆用户的对象
     */
    public UserLoginEvent(User user) {
        super(user);
    }
}