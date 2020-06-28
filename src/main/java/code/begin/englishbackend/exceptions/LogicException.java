package code.begin.englishbackend.exceptions;

public class LogicException extends Exception {
    private final ErrorCode errorCode;

    public LogicException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public LogicException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public LogicException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}

