package kane.exercise.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * java.lang.OutOfMemoryError: PermGen space
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 * JDK1.6编译
 *
 * @author kane
 */
public class PermGenSpaceOomTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}