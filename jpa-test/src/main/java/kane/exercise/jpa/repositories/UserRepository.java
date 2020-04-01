package kane.exercise.jpa.repositories;

import java.util.List;

import kane.exercise.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author kane
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 根据id列表获取用户信息
     *
     * @param ids id列表
     * @return 匹配到的user列表
     */
//    @Query(value = "select * from user where 1=1 and id in(?#{T(org.springframework.util.CollectionUtils).isEmpty(\"[0]\")?\"[0]\":2})", nativeQuery = true)
    @Query(value = "select * from user where 1=1 and if(?#{T(org.springframework.util.CollectionUtils).isEmpty([0])?'empty':'not empty'} = 'not empty' , id in (?1), 1=1)", nativeQuery = true)
//    @Query(value = "select * from user where 1=1 and id in (?1)", nativeQuery = true)
    List<User> findUserByIds(@Param("ids") List<Long> ids);

}