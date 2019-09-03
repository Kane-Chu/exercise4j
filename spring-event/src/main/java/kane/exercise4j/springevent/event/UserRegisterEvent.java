package kane.exercise4j.springevent.event;

import kane.exercise4j.springevent.bean.User;

/**
 * @author kane
 */
public class UserRegisterEvent extends UserEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param user 注册用户的对象
     */
    public UserRegisterEvent(User user) {
        super(user);
    }
}