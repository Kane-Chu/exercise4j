package kane.exercise.concurrency;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.Test;

/**
 * 原子类测试
 * {@link java.util.concurrent.atomic.AtomicReference}
 * {@link java.util.concurrent.atomic.AtomicIntegerFieldUpdater}
 *
 * @author kane
 */
public class AtomicTest {

    @Test
    public void atomicReferenceTest() {
        AtomicReference<Integer> atomicReference = new AtomicReference<>(0);
        atomicReference.compareAndSet(0, 2); // 如果当前值为0，将其更新为2 更新成功
        atomicReference.compareAndSet(0, 1); // 如果当前值为0，将其改为1   更新不成功
        atomicReference.compareAndSet(1, 3); // 如果当前值为1，将其改为3   更新不成功
        atomicReference.compareAndSet(2, 4); // 如果当前值为2，将其改为4   更新成功
        atomicReference.compareAndSet(3, 5); // 如果当前值为3，将其改为5   更新不成功
        System.out.println("atomicReference=" + atomicReference);
    }

    @Test
    public void atomicIntegerFieldUpdaterTest() {
        AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterTest> updater
                = AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterTest.class, "count");

        AtomicIntegerFieldUpdaterTest updaterTest = new AtomicIntegerFieldUpdaterTest();

        if (updater.compareAndSet(updaterTest, 100, 120)) {
            System.out.println("update success " + updaterTest.getCount());
        }

        if (updater.compareAndSet(updaterTest, 100, 130)) {
            System.out.println("update success " + updaterTest.getCount());
        } else {
            System.out.println("update fail " + updaterTest.getCount());
        }
    }

    static class AtomicIntegerFieldUpdaterTest {
        /*
         * 1. 必须是基本类型，不能使包装类
         * 2. 必须以 volatile 修饰
         * 3. 调用者必须可以直接访问
         * 4. 不能是static和final的
         * */
        public volatile int count = 100;

        public int getCount() {
            return count;
        }
    }


}