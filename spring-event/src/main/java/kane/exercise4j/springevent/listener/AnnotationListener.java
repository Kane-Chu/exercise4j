package kane.exercise4j.springevent.listener;

import kane.exercise4j.springevent.bean.User;
import kane.exercise4j.springevent.event.UserEvent;
import kane.exercise4j.springevent.event.UserLoginEvent;
import kane.exercise4j.springevent.event.UserRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author kane
 */
@Slf4j
@Component
public class AnnotationListener {

    @EventListener
    @Order(Ordered.HIGHEST_PRECEDENCE) // 这里最先监听到
    public void onUserLogin(UserLoginEvent event){
        log.info("listening in AnnotationListener.onUserLogin ...");
        User user = (User) event.getSource();
        log.info("user[{}] login ...", user.getUid());
    }

    @EventListener
    @Order(Ordered.HIGHEST_PRECEDENCE) // 这里最先监听到
    public void onUserRegister(UserRegisterEvent event){
        log.info("listening in AnnotationListener.onUserRegister ...");
        User user = (User) event.getSource();
        log.info("user[{}] register ...", user.getUid());
    }

    @EventListener({UserLoginEvent.class,UserRegisterEvent.class})
    public void onUserStatusChange(UserEvent event) throws InterruptedException {
        log.info("listening in AnnotationListener.onUserStatusChange ...");
        User user = (User) event.getSource();
        if(event instanceof UserLoginEvent){
            log.info("user[{}] login ...", user.getUid());
        }else if(event instanceof UserRegisterEvent){
            log.info("user[{}] register ...", user.getUid());
        }
        // 时间发送和时间处理是在同一个线程里
        Thread.sleep(1000);
        log.info("user status listener end");
    }


}