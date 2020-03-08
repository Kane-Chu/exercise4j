package kane.exercise.migrationflyway.service;

/**
 * @author kane
 */
public interface UserService {
    void create(String name, Integer age);

    void deleteByName(String name);

    Integer getAllUsers() throws Exception;

    void deleteAllUsers();
}