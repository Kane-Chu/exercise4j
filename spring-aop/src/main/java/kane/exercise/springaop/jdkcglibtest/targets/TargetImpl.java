package kane.exercise.springaop.jdkcglibtest.targets;

/**
 * 被代理对象
 *
 * @author kane
 */
public class TargetImpl implements Target {
    @Override
    public int test(int i) {
        return i + 1;
    }
}