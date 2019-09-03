package kane.exercise.commons.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;

import org.slf4j.Logger;
import org.slf4j.helpers.MessageFormatter;

public class ExceptionHelper {
    private ExceptionHelper() {
    }

    public static RuntimeException wrap(Exception cause) {
        if (cause instanceof RuntimeException) {
            return (RuntimeException) cause;
        }
        return new WrapRuntimeException(cause);
    }

    public static Exception unwrap(Exception exception) {
        if (exception == null) {
            return null;
        }
        if (exception instanceof WrapRuntimeException) {
            return (Exception) exception.getCause();
        }
        return exception;
    }

    public static String getStackTrace(Throwable throwable) {
        StringWriter writer = new StringWriter();
        throwable.printStackTrace(new PrintWriter(writer, true));
        return writer.toString();
    }

    public static <T extends Exception> T except(
            Class<T> clazz, String message, Object... arguments
    ) {
        return except(clazz, null, null, null, message, arguments);
    }

    public static <T extends Exception> T except(
            Class<T> clazz, Logger logger, String message, Object... arguments
    ) {
        return except(clazz, null, null, logger, message, arguments);
    }

    public static <T extends Exception> T except(
            Class<T> clazz, Throwable cause, String message, Object... arguments
    ) {
        return except(clazz, cause, null, null, message, arguments);
    }

    public static <T extends Exception> T except(
            Class<T> clazz, Throwable cause, Logger logger, String message, Object... arguments
    ) {
        return except(clazz, cause, null, logger, message, arguments);
    }

    public static <T extends Exception> T except(
            Class<T> clazz, ErrorCode errorCode, String message, Object... arguments
    ) {
        return except(clazz, null, errorCode, null, message, arguments);
    }

    public static <T extends Exception> T except(
            Class<T> clazz, ErrorCode errorCode, Logger logger, String message, Object... arguments
    ) {
        return except(clazz, null, errorCode, logger, message, arguments);
    }

    public static <T extends Exception> T except(
            Class<T> clazz, Throwable cause, ErrorCode errorCode, String message, Object... arguments
    ) {
        return except(clazz, cause, errorCode, null, message, arguments);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Exception> T except(
            T cause, Logger logger, String message, Object... arguments
    ) {
        return except(null, cause, null, logger, message, arguments);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Exception> T except(
            Class<T> clazz, Throwable cause, ErrorCode errorCode,
            Logger logger, String message, Object... arguments
    ) {
        if (arguments != null && arguments.length > 0) {
            message = MessageFormatter.arrayFormat(message, arguments).getMessage();
        }

        if (logger != null) {
            if (cause == null) {
                logger.error(message);

            } else {
                logger.error(message, cause);
            }
        }

        if (clazz == null) {
            return (T) cause;
        }

        T exception;

        try {
            Constructor<T> constructor = clazz.getConstructor(String.class);
            exception = constructor.newInstance(message);
            if (errorCode != null && exception instanceof WithErrorCode) {
                ((WithErrorCode) exception).setErrorCode(errorCode);
            }

        } catch (ReflectiveOperationException e) {
            String info = "Cannot create except <" + clazz.getName() + "> with message";
            if (logger != null) {
                logger.error(info, e);
            }
            throw new RuntimeException(info, e);
        }

        if (cause != null) {
            exception.initCause(cause);
        }

        return exception;
    }
}
