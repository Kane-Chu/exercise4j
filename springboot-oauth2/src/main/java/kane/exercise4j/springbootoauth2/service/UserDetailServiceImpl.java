package kane.exercise4j.springbootoauth2.service;

import java.util.Collections;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * token刷新的时候也会调用此服务，所以开启了refresh_token必须有此服务
 *
 * @author kane
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {
    @Reference
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return User.withUsername(username)
                .password("{noop}pass1234")
                .authorities(Collections.emptyList())
                .build();
    }
}