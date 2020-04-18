package kane.exercise.concurrency;

import kane.exercise.commons.random.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StopWatch;

/**
 * {@link BlockedQueue} 测试类
 *
 * @author kane
 */
public class BlockedQueueTest {

    private StopWatch stopWatch;

    private static SnowflakeIdWorker idWorker;

    @Before
    public void init() {
        stopWatch = new StopWatch("BlockedQueueTest");
        idWorker = new SnowflakeIdWorker(0, 0);
    }


    @Test
    public void test() throws InterruptedException {
        stopWatch.start("Build");
        BlockedQueue<Long> queue = new BlockedQueue<>();
        stopWatch.stop();
        stopWatch.start("Producer");
        Producer p = new Producer(queue);
        stopWatch.stop();
        stopWatch.start("Consumer1");
        Consumer c1 = new Consumer(queue, "1");
        stopWatch.stop();
        stopWatch.start("Consumer2");
        Consumer c2 = new Consumer(queue, "2");
        stopWatch.stop();
        Thread threadP = new Thread(p);
        Thread threadC1 = new Thread(c1);
        Thread threadC2 = new Thread(c2);
        threadP.start();
        threadC1.start();
        threadC2.start();

        threadP.join();
        threadC1.join();
        threadC2.join();

    }

    @After
    public void after() {
        System.out.println();
        System.out.println(stopWatch.prettyPrint());
    }

    /**
     * 生产者
     */
    @Slf4j
    static class Producer implements Runnable {
        private final BlockedQueue<Long> queue;

        Producer(BlockedQueue<Long> queue) {
            this.queue = queue;
        }

        public void run() {
            while (true) {
                queue.enq(produce());
            }
        }

        Long produce() {
            long id = idWorker.nextId();
            log.info("produce:{}", id);
            return id;
        }

    }

    /**
     * 消费者
     */
    @Slf4j
    static class Consumer implements Runnable {
        private final BlockedQueue<Long> queue;
        private final String name;

        Consumer(BlockedQueue<Long> queue, String name) {
            this.queue = queue;
            this.name = name;
        }

        public void run() {
            while (true) {
                consume(queue.deq());
            }
        }

        void consume(Long ele) {
            log.info("consume {}:{}", name, ele);
        }
    }


}