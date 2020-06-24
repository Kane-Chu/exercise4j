package kane.exercise.concurrency.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

/**
 * Actor 模型测试 依赖 akka 框架
 * <p>
 * 在 Actor 模型中，所有的计算都是在 Actor 中执行的
 * <p>
 * 在 Actor 模型里，一切都是 Actor，并且 Actor 之间是完全隔离的，不会共享任何变量
 *
 * @author kane
 */
public class HelloActor extends UntypedAbstractActor {


    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println("Hello " + message);
    }

    public static void main(String[] args) {
        //创建Actor系统
        ActorSystem system = ActorSystem.create("HelloSystem");
        // 创建HelloActor
        ActorRef helloActor = system.actorOf(Props.create(HelloActor.class));
        //发送消息给HelloActor
        helloActor.tell("Actor", ActorRef.noSender());
    }
}