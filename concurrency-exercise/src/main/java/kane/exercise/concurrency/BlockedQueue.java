package kane.exercise.concurrency;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

/**
 * {@link java.util.concurrent.BlockingQueue}
 * 管程模型-同步
 * <p>
 * <p>1.对于入队操作，如果队列已满，就需要等待直到队列不满，所以这里用了notFull.await();
 * <p>2.对于出队操作，如果队列为空，就需要等待直到队列不空，所以就用了notEmpty.await();
 * <p>3.如果入队成功，那么队列就不空了，就需要通知条件变量：队列不空notEmpty对应的等待队列;
 * <p>4.如果出队成功，那就队列就不满了，就需要通知条件变量：队列不满notFull对应的等待队列;
 *
 * @author kane
 */
@Slf4j
public class BlockedQueue<T> {

    private List<T> queue = new Vector<>();

    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    private int size;

    public BlockedQueue() {
        this.size = 10;
    }

    public BlockedQueue(int size) {
        this.size = size;
    }

    public void enq(T ele) {
        lock.lock();
        try {
            while (queue.size() == this.size) {
                notFull.await();
            }
            queue.add(ele);
            notEmpty.signal();
        } catch (InterruptedException e) {
            log.error("error", e);
        } finally {
            lock.unlock();
        }
    }

    public T deq() {
        lock.lock();
        try {
            while (queue.size() == 0) {
                notEmpty.await();
            }
            T ele = queue.remove(0);
            notFull.signal();
            return ele;
        } catch (InterruptedException e) {
            log.error("error", e);
        } finally {
            lock.unlock();
        }
        return null;
    }


}