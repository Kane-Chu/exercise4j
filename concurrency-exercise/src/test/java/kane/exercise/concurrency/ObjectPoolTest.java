package kane.exercise.concurrency;

import kane.exercise.commons.random.SnowflakeIdWorker;
import org.junit.Test;

/**
 * {@link ObjectPool} 测试类
 *
 * @author kane
 */
public class ObjectPoolTest {

    private static final SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);

    @Test
    public void test() throws InterruptedException {
        ObjectPool<InternalClass, String> pool = new ObjectPool<>(10, new InternalClass());
        String result = pool.exec(InternalClass::print);
        System.out.println(result);
    }

    static class InternalClass {
        public String print() {
            return Long.toString(idWorker.nextId());
        }
    }
}