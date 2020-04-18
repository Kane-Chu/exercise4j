package kane.exercise.concurrency;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

/**
 * {@link java.util.concurrent.CyclicBarrier} 测试类
 *
 * @author kane
 */
public class CyclicBarrierTest {

    private Executor executor = Executors.newFixedThreadPool(1);

    private static final List<String> DATA = Lists.newArrayList("r1", "r2", "r3", "r4", "r5");

    private final CyclicBarrier barrier
            = new CyclicBarrier(2, () -> executor.execute(this::print));

    @Test
    public void test() throws InterruptedException {
        Thread T1 = new Thread(() -> {
            while (!CollectionUtils.isEmpty(DATA)) {
                String record = DATA.get(0);
                System.out.println("process " + record + " in thread 1");
                try {
                    // 减一后等待 barrier 计数器为0
                    barrier.await();
                    // 为了等待 回调 执行完成
                    Thread.sleep(100);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        T1.start();

        Thread T2 = new Thread(() -> {
            while (!CollectionUtils.isEmpty(DATA)) {
                String record = DATA.get(0);
                System.out.println("process " + record + " in thread 2");
                try {
                    // 减一后等待 barrier 计数器为0
                    barrier.await();
                    // 为了等待 回调 执行完成
                    Thread.sleep(100);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        T2.start();

        T1.join();
        T2.join();
    }

    /**
     * T1 T2 每次执行完一次循环后都执行此函数
     */
    private void print() {
        String record = DATA.remove(0);
        System.out.println(record + " process end");
    }
}