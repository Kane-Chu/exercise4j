package kane.exercise.commons.exception;

import org.springframework.util.Assert;

public class WrapRuntimeException extends RuntimeException implements WithErrorCode {

    private ErrorCode errorCode = new ErrorCode("99999999", "系统异常");

    public WrapRuntimeException(Exception cause) {
        super(cause);
        Assert.notNull(cause, "{cause} cannot be null");
        if (cause instanceof WithErrorCode) {
            errorCode = ((WithErrorCode) cause).getErrorCode();
        }
    }

    @Override
    public Exception getCause() {
        return (Exception) super.getCause();
    }

    @Override
    public Throwable initCause(Throwable cause) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setErrorCode(ErrorCode errorCode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
