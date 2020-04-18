package kane.exercise.commons.test.base;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

/**
 * @author kane
 */
public class BasicTest {
    @Test
    public void test1(){
        A a = new B();
        a.a1();
        a.a2();
    }

    class A {
        private void a1(){
            System.out.println("1111");
        }
        public void a2(){
            System.out.println("222");
        }
    }

    class B extends A{
        @Override
        public void a2() {
            System.out.println("b2222222");
        }
    }

}