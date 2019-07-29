package kane.exercise4j.springbootoauth2.controller;

import kane.exercise4j.springbootoauth2.controller.bean.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kane
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/users/current")
    public UserInfo getUserInfo(){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(user.getUsername());
        userInfo.setMobile("12345678901");
        userInfo.setMail("123@qq.com");
        userInfo.setAddress("Shang Hai");
        return userInfo;
    }
}