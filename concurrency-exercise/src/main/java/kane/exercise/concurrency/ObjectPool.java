package kane.exercise.concurrency;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * 对象池 用信号量实现一个限流器 {@link java.util.concurrent.Semaphore}
 * T 对象池中的类
 * R 对象函数的返回值
 *
 * @author kane
 */
public class ObjectPool<T, R> {
    private final List<T> pool;
    private final Semaphore sem;

    public ObjectPool(int size, T t) {
        pool = new Vector<T>() {
        };
        for (int i = 0; i < size; i++) {
            pool.add(t);
        }
        sem = new Semaphore(size);
    }

    /**
     * 利用对象池中的对象调用 func
     *
     * @param func 需要调用的 func
     * @return func的结果
     * @throws InterruptedException 参考 {@link Semaphore#acquire()}
     */
    public R exec(Function<T, R> func) throws InterruptedException {
        T t = null;
        sem.acquire();
        try {
            t = pool.remove(0);
            return func.apply(t);
        } finally {
            pool.add(t);
            sem.release();
        }
    }


}