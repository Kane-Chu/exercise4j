package kane.exercise.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Test;

/**
 * {@link MyThreadPool} 测试类
 *
 * @author kane
 */
public class MyThreadPoolTest {
    @Test
    public void test() throws InterruptedException {
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        MyThreadPool threadPool = new MyThreadPool(10, workQueue);
        threadPool.execute(() -> System.out.println("test"));
    }
}