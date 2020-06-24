package kane.exercise.concurrency.actor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

/**
 * 使用 Actor 模式上线 累加器
 *
 * @author kane
 */
public class CounterActor extends UntypedAbstractActor {
    private int counter = 0;

    @Override
    public void onReceive(Object message) throws Throwable {
        //如果接收到的消息是数字类型，执行累加操作，
        //否则打印counter的值
        if (message instanceof Number) {
            counter += ((Number) message).intValue();
        } else {
            System.out.println(counter);
        }
    }

    public static void main(String[] args) throws Exception {
        //创建Actor系统
        ActorSystem system = ActorSystem.create("CounterSystem");
        //4个线程生产消息
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                4,
                4,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                r -> new Thread(r, "count thread"));

        //创建CounterActor
        ActorRef counterActor = system.actorOf(Props.create(CounterActor.class));
        //生产4*100000个消息
        for (int i = 0; i < 4; i++) {
            executor.execute(() -> {
                for (int j = 0; j < 100000; j++) {
                    counterActor.tell(1, ActorRef.noSender());
                }
            });
        }
        //关闭线程池
        executor.shutdown();
        //等待CounterActor处理完所有消息
        Thread.sleep(1000);
        //打印结果
        counterActor.tell("碰到我就打印", ActorRef.noSender());
        //关闭Actor系统
        system.stop(counterActor);

    }
}