package kane.exercise.oom;

/**
 * java.lang.StackOverflowError
 * -Xss64k
 *
 * @author kane
 */
public class StackOverflowTest {
    public static void main(String[] args) {
        stackOutOfMemoryError(1);
    }

    public static void stackOutOfMemoryError(int depth) {
        depth++;
        System.out.println(depth);
        stackOutOfMemoryError(depth);
    }
}