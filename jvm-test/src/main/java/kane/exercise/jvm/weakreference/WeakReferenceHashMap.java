package kane.exercise.jvm.weakreference;

import java.util.WeakHashMap;

import lombok.Data;
import org.assertj.core.api.Assertions;

/**
 * ${@link java.util.WeakHashMap} 测试
 * 适用于缓存场景
 *
 * @author kane
 */
public class WeakReferenceHashMap {
    public static void main(String[] args) throws InterruptedException {
        KeyEntity keyEntity = new KeyEntity();
        keyEntity.setKey("1");
        WeakHashMap<KeyEntity, String> map = new WeakHashMap<>();
        map.put(keyEntity, "hello");
        Assertions.assertThat(map.containsKey(keyEntity)).isTrue();

        // 释放key
        keyEntity = null;
        System.gc();

        // 等待gc完成
        Thread.sleep(1_000);
        // entity将被回收
        Assertions.assertThat(map.isEmpty()).isTrue();

    }

    @Data
    static class KeyEntity{
        private String key;
    }
}