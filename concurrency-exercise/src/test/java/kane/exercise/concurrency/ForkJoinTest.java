package kane.exercise.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import org.junit.After;
import org.junit.Test;
import org.springframework.util.StopWatch;

/**
 * Fork/Join 测试
 *
 * @author kane
 */
public class ForkJoinTest {

    private StopWatch watch = new StopWatch("Fork/Join");

    @Test
    public void fibonacci() {
        watch.start("fibonacci");
        // 创建分治任务线程池
        ForkJoinPool fjp = new ForkJoinPool(4);
        // 创建分治任务
        Fibonacci fib = new Fibonacci(30);
        // 启动分治任务
        Integer result = fjp.invoke(fib);
        // 输出结果
        System.out.println(result);
        watch.stop();
    }


    @Test
    public void mr() {
        watch.start("mr");
        String[] fc = {"hello world", "hello me", "hello fork", "hello join", "fork join in world"};
        ForkJoinPool pool = new ForkJoinPool(3);

        MR mr = new MR(fc, 0, fc.length);
        Map<String, Long> result = pool.invoke(mr);
        System.out.println(result);
        watch.stop();
    }

    @After
    public void after(){
        System.out.println(watch.prettyPrint());
    }

    // 递归任务 斐波那契数列
    static class Fibonacci extends RecursiveTask<Integer> {
        final int n;

        Fibonacci(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            }
            Fibonacci f1 = new Fibonacci(n - 1);
            // 创建子任务
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);
            // 等待子任务结果，并合并结果
            return f2.compute() + f1.join();
        }
    }

    static class MR extends RecursiveTask<Map<String, Long>> {

        private String[] fc;
        private int start, end;

        MR(String[] fc, int fr, int to) {
            this.fc = fc;
            this.start = fr;
            this.end = to;
        }

        @Override
        protected Map<String, Long> compute() {
            if (end - start == 1) {
                return calc(fc[start]);
            } else {
                int mid = (start + end) / 2;
                MR mr1 = new MR(fc, start, mid);
                mr1.fork();
                MR mr2 = new MR(fc, mid, end);
                //计算子任务，并返回合并的结果
                return merge(mr2.compute(), mr1.join());
            }
        }

        private Map<String, Long> merge(Map<String, Long> r1, Map<String, Long> r2) {
            Map<String, Long> result = new HashMap<>(r1);
            r2.forEach((k, v) -> result.merge(k, v, Long::sum));
            return result;
        }

        // 统计单词数量
        private Map<String, Long> calc(String line) {
            Map<String, Long> result = new HashMap<>();
            //分割单词
            String[] words = line.split("\\s+");

            //统计单词数量
            for (String w : words) {
                Long v = result.get(w);
                if (v != null) {
                    result.put(w, v + 1);
                } else {
                    result.put(w, 1L);
                }
            }
            return result;
        }
    }


}