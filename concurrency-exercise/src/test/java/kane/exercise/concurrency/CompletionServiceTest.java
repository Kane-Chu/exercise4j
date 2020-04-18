package kane.exercise.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * {@link java.util.concurrent.CompletionService} 测试类
 *
 * @author kane
 */
@Slf4j
public class CompletionServiceTest {


    @Test
    public void test() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
        List<Future<Integer>> futures = new ArrayList<>(3);
        // 提交异步任务，并保存future到futures
        futures.add(cs.submit(this::slow));
        futures.add(cs.submit(this::mid));
        futures.add(cs.submit(this::fast));

        Integer result = 0;
        try {
            // 获取最快返回的任务执行结果
            for (int i = 0; i < 3; i++) {
                result = cs.take().get();
                if (result != null) {
                    break;
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // 取消所有任务
            for (Future<Integer> future : futures) {
                // 会抛出 InterruptedException
                future.cancel(true);
            }
        }
        System.out.println(result);

    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        log.info("start");
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(this::slow);
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(this::mid);
        CompletableFuture<Integer> f3 = CompletableFuture.supplyAsync(this::fast);

        CompletableFuture<Integer> result = f1.applyToEither(f2, r -> r).applyToEither(f3, r -> r);
        System.out.println(result.get());
        log.info("end");
    }

    public Integer fast() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("fast return");
        return 1;
    }

    public Integer mid() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("mid return");
        return 2;
    }

    public Integer slow() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("slow return");
        return 3;
    }

}