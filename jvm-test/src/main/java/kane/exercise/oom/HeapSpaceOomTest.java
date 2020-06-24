package kane.exercise.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * java.lang.OutOfMemoryError: Java heap space
 * -Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/kane/Documents/temp/111111/
 *
 * @author kane
 */
public class HeapSpaceOomTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(i++);
        }
    }
}