package kane.exercise.commons.random;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StopWatch;

/**
 * TODO
 *
 * @author kane
 */
@Slf4j
public class SnowflakeIdWorkerTest {

    private StopWatch stopWatch;

    @Before
    public void init() {
        stopWatch = new StopWatch("SnowflakeIdWorkerTest");
    }

    @Test
    public void test() {
        stopWatch.start();
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        for (int i = 0; i < 10; i++) {
            long id = idWorker.nextId();
            log.info(Long.toBinaryString(id));
            log.info(Long.toString(id));
        }
        stopWatch.stop();
    }

    @After
    public void after() {
        System.out.println();
        System.out.println(stopWatch.prettyPrint());
    }
}