package code.begin.englishbackend.common.api;

public enum  ResultCode implements IErrorCode {

    SUCCESS(200, "Successful"),
    FAILED(500, "Failed"),
    VALIDATE_FAILED(404, "Parameter check failed"),
    UNAUTHORIZED(401, "Not logged in or the token has expired"),
    FORBIDDEN(403, "No related permissions");

    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

