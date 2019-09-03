package kane.exercise.commons.exception;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;


public class ErrorCode implements Serializable {

    private final String code;
    private final String description;

    protected ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorCode errorCode = (ErrorCode) o;
        return Objects.equals(code, errorCode.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ErrorCode.class.getSimpleName() + "[", "]")
                .add("code='" + code + "'")
                .add("description='" + description + "'")
                .toString();
    }
}
