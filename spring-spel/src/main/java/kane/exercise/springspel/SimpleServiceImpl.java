package kane.exercise.springspel;

/**
 * @author kane
 */
public class SimpleServiceImpl implements SimpleService {
    @Override
    public void sayHello(String name) {
        System.out.println("hello " + name);
    }

    @Override
    public String returnHello(String name) {
        return "hello " + name;
    }
}