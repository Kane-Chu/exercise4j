package kane.exercise.performanceexercise;

import org.springframework.util.StopWatch;

/**
 * 代码编译时机 及 阈值测试
 * <p>
 * -XX:CompileThreshold 用于设置编译阈值 当方法调用次数 或 循环回边数的总和达到此数值时触发编译
 *
 * @author kane
 */
public class Compilation {

    private static StopWatch stopWatch = new StopWatch("compilation");

    @SuppressWarnings("UnusedReturnValue")
    private static String print(int i) {
        return "index: " + i;
    }

    public static void main(String[] args) {
        int i = 1;
        while (i <= 20000) {
            stopWatch.start("idx - " + i);
            //noinspection ResultOfMethodCallIgnored
            print(i);
            i++;
            stopWatch.stop();
        }
        System.out.println(stopWatch.prettyPrint());
    }
}