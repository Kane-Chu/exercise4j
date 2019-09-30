package kane.exercise.springspel;

import java.util.UUID;

import kane.exercise.springspel.bean.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StopWatch;

/**
 * @author kane
 */
public class SimpleTest {

    private ExpressionParser parser;
    StopWatch stopWatch;


    @Before
    public void init() {
        parser = new SpelExpressionParser();
        stopWatch = new StopWatch("simple test");
    }

    @After
    public void after() {
        System.out.println();
        System.out.println(stopWatch.prettyPrint());
    }

    @Test
    public void simple() throws NoSuchMethodException {
        stopWatch.start("simple1");
        Expression expression1 = parser.parseExpression("'Hello'.concat(' world')");
        String result1 = expression1.getValue(String.class);
        System.out.println("result:" + result1);
        stopWatch.stop();

        User user = User.builder()
                .uid(UUID.randomUUID().toString())
                .name("kane").build();

        stopWatch.start("simple2");
        EvaluationContext context2 = new StandardEvaluationContext(user);
        Expression expression2 = parser.parseExpression("'my name is '.concat(name)");
        String result2 = expression2.getValue(context2, String.class);
        System.out.println("result:" + result2);
        stopWatch.stop();

        stopWatch.start("simple3");
        EvaluationContext context3 = new StandardEvaluationContext(user);
        parser.parseExpression("name").setValue(context3, "Tom");
        System.out.println("after set:" + user);
        stopWatch.stop();

        stopWatch.start("simple4");
        Expression expression4 = parser.parseExpression("new String('hello world').toUpperCase()");
        String result4 = expression4.getValue(String.class);
        System.out.println("result:" + result4);
        stopWatch.stop();

        stopWatch.start("simple5");
        Expression expression5 = parser.parseExpression("name == 'Tom'");
        Boolean result5 = expression5.getValue(user, Boolean.class);
        System.out.println("result:" + result5);
        stopWatch.stop();

        SimpleService simpleService = new SimpleServiceImpl();

        stopWatch.start("simple6");
        StandardEvaluationContext context6 = new StandardEvaluationContext(simpleService);
        context6.registerFunction("sayHello",
                SimpleServiceImpl.class.getDeclaredMethod("sayHello", String.class));
        context6.registerFunction("returnHello",
                SimpleServiceImpl.class.getDeclaredMethod("returnHello", String.class));
        Expression expression6 = parser.parseExpression("sayHello('kane')");
        expression6.getValue(context6);
        expression6 = parser.parseExpression("returnHello('kane')");
        String result6 = expression6.getValue(context6, String.class);
        System.out.println("result:" + result6);
        stopWatch.stop();


    }


}