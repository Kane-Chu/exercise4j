package kane.exercise.springaop.jdkcglibtest.targets;

/**
 * 被代理接口，JDK动态代理使用
 *
 * @author kane
 */
public interface Target {
    /**
     * test
     *
     * @param i test
     * @return test
     */
    int test(int i);
}