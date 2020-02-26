package kane.exercise.common.test.base;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

/**
 * @author kane
 */
public class BasicTest {
    @Test
    public void test1(){
        Path p = Paths.get("/");
        p = p.resolve("page");
        p = p.resolve("");
        p = p.resolve("2");
        p = p.resolve("2");
        System.out.println(p.toString());
    }
}