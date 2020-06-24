package kane.exercise.jooq.service;

import java.util.List;

import kane.exercise.jooq.generated.tables.pojos.User;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

/**
 * 用户
 *
 * @author kane
 */
@Service
public class UserService {

    private final DSLContext create;


    public UserService(DSLContext create) {
        this.create = create;
    }

    public List<User> getUsers(){
        return null;
    }

}