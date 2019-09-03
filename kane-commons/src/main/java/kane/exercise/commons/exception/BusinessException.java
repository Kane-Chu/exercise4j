package kane.exercise.commons.exception;


public class BusinessException extends Exception implements WithErrorCode {

    private ErrorCode errorCode = getDefaultErrorCode();

    @Override
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getDescription(), cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getDefaultErrorCode() {
        return CoreErrorCode.UNKNOWN;
    }
}
