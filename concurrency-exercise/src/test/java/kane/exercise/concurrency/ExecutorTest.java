package kane.exercise.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.Data;
import org.junit.Test;

/**
 * {@link java.util.concurrent.Executor} 测试类
 *
 * @author kane
 */
public class ExecutorTest {
    @Test
    public void test() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Result result = new Result();
        result.setF1("f1");
        Future<Result> future = executorService.submit(new Task(result), result);
        result = future.get();
        System.out.println(result);

    }

    static class Task implements Runnable {
        private Result result;

        public Task(Result result) {
            this.result = result;
        }

        @Override
        public void run() {
            System.out.println(result.getF1());
            result.setF2("f2");
        }
    }

    @Data
    static class Result {
        private String f1;
        private String f2;
    }

}