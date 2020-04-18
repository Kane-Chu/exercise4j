package kane.exercise.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import lombok.extern.slf4j.Slf4j;

/**
 * 利用 生产者 - 消费者模式 实现一个线程池
 * <p>
 * 其中线程池的使用者为生产者 线程池为消费者 产品为{@link Runnable}对象
 *
 * @author kane
 */
public class MyThreadPool {
    /**
     * 利用阻塞队列实现生产者-消费者模式
     */
    private BlockingQueue<Runnable> workQueue;

    List<WorkerThread> threads = new ArrayList<>();

    public MyThreadPool(int poolSize, BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
        for (int idx = 0; idx < poolSize; idx++) {
            WorkerThread work = new WorkerThread();
            work.start();
            threads.add(work);
        }
    }

    public void execute(Runnable command) throws InterruptedException {
        workQueue.put(command);
    }

    /**
     * 工作线程负责消费任务，并执行任务
     */
    class WorkerThread extends Thread {
        @Override
        public void run() {
            while (true) {
                Runnable task;
                try {
                    task = workQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                task.run();
            }
        }
    }
}