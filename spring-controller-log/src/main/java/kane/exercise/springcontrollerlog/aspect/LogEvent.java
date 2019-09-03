package kane.exercise.springcontrollerlog.aspect;

import java.util.LinkedHashMap;
import java.util.Map;

import kane.exercise.commons.helper.RegexHelper;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogEvent {

    public static final String URI = "uri";
    public static final String TOKEN = "token";
    public static final String REMOTE_ADDR = "remoteAddr";

    private String clazz;
    private String function;
    private Logger logger;
    private Map<String, Object> context;

    private final long timestamp;

    public LogEvent(JoinPoint joinPoint) {
        clazz = joinPoint.getThis().getClass().getName();
        clazz = RegexHelper.replaceAll(clazz, "\\$\\$.*", "");

        logger = LoggerFactory.getLogger(clazz);
        function = joinPoint.getSignature().getName();

        this.timestamp = System.currentTimeMillis();
    }

    public String getSimpleClazz() {
        int index = clazz.lastIndexOf(".");
        return clazz.substring(index + 1);
    }

    public String getFunction() {
        return function;
    }

    public Logger getLogger() {
        return logger;
    }

    public Map<String, Object> getContext() {
        if (context == null) {
            context = new LinkedHashMap<>();
        }
        return context;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return context == null ? null : (T) context.get(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T put(String key, Object value) {
        return (T) getContext().put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T remove(String key) {
        return context == null ? null : (T) context.remove(key);
    }

    public final long getTimestamp() {
        return this.timestamp;
    }
}
