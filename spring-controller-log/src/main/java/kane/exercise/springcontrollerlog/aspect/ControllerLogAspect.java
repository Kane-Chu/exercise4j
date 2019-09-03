package kane.exercise.springcontrollerlog.aspect;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import kane.exercise.commons.exception.BusinessException;
import kane.exercise.commons.exception.ErrorCode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static kane.exercise.springcontrollerlog.aspect.LogEvent.*;


@Aspect
@Component
@Order(1000)
public class ControllerLogAspect {

    private ThreadLocal<LogEvent> events = new ThreadLocal<>();

    public static final String HEADER_FORWARDED_FOR = "X-Forwarded-For";

    @Pointcut("@within(org.springframework.stereotype.Controller) || " +
            "@within(org.springframework.web.bind.annotation.RestController)")
    public void controllerClass() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PatchMapping)")
    public void requestMapping() {
    }

    @Pointcut("controllerClass() && requestMapping()")
    public void controllerMethod() {
    }

    @Before("controllerMethod()")
    public void doBefore(JoinPoint joinPoint) {
        LogEvent event = events.get();
        if (event != null) {
            return;
        }

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes)) {
            return;
        }

        event = new LogEvent(joinPoint);

        Logger logger = event.getLogger();
        if (!logger.isDebugEnabled()) {
            return;
        }

        logger.debug("/***********************");
        logger.debug(" * START {}.{}()", event.getSimpleClazz(), event.getFunction());

        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        event.put(URI, getUriWithQueryString(request));
        event.put(REMOTE_ADDR, getRemoteAddr(request));
        event.put(TOKEN, request.getParameter("token"));
        for (Map.Entry<String, Object> entry : event.getContext().entrySet()) {
            logger.debug(" * {} = {}", entry.getKey(), entry.getValue());
        }
        logger.debug(" ***********************/");
    }

    @AfterReturning("controllerMethod()")
    public void doAfterReturning() {
        LogEvent event = events.get();
        if (event == null) {
            return;
        }

        events.remove();

        Logger logger = event.getLogger();
        long now = System.currentTimeMillis();

        logger.debug("/***********************");
        logger.debug(" * END {}.{}()", event.getSimpleClazz(), event.getFunction());
        logger.debug(" * elapsed = {}ms", now - event.getTimestamp());
        logger.debug(" ***********************/");
    }

    @AfterThrowing(pointcut = "controllerMethod()", throwing = "exception")
    public void doAfterThrowing(Exception exception) {
        LogEvent event = events.get();
        if (event == null) {
            return;
        }

        events.remove();

        Logger logger = event.getLogger();
        long now = System.currentTimeMillis();

        logger.error("/***********************");
        logger.error(" * ERROR {}.{}()", event.getSimpleClazz(), event.getFunction());
        logger.error(" * elapsed = {}ms", now - event.getTimestamp());

        if (exception instanceof BusinessException) {
            ErrorCode code = ((BusinessException) exception).getErrorCode();
            logger.error(" * error = {}", code);
            logger.error(" ***********************/", exception);
        } else {
            logger.error(" ***********************/", exception);
        }
    }

    private String getUriWithQueryString(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        return queryString == null ? uri : uri + "?" + queryString;
    }

    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getHeader(HEADER_FORWARDED_FOR);
        if (remoteAddr == null) {
            remoteAddr = request.getRemoteAddr();
        }
        return remoteAddr;
    }


}
