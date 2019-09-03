package kane.exercise.commons.exception;

public class CoreErrorCode extends ErrorCode {

    public static CoreErrorCode SUCCESS =
            new CoreErrorCode("00000000", "成功");

    public static CoreErrorCode BAD_REQUEST =
            new CoreErrorCode("CORE4000", "不合法的请求");

    public static CoreErrorCode VALIDATION_ERROR =
            new CoreErrorCode("CORE4001", "校验错误");

    public static CoreErrorCode TEMPLATE_ERROR =
            new CoreErrorCode("CORE6000", "模板错误");

    public static CoreErrorCode UNKNOWN =
            new CoreErrorCode("CORE9999", "服务异常稍后再试");

    protected CoreErrorCode(String code, String description) {
        super(code, description);
    }
}
