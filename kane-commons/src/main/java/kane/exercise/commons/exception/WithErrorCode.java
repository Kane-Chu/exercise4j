package kane.exercise.commons.exception;

public interface WithErrorCode {
    void setErrorCode(ErrorCode errorCode);
    ErrorCode getErrorCode();
}
