package code.begin.englishbackend.exceptions;

public enum ErrorCode {
    INTERNAL_SERVER_ERROR("500", "Internal server error"),
    FORBIDDEN("403", "Permission denied"),
    RECORD_NOT_FOUND("003", "RECORD_NOT_FOUND"),
    RECORD_NOT_EXISTED("004", "RECORD_NOT_EXISTED"),
    UPLOAD_FILE_FAILED("007", "Upload file failed"),
    EMAIL_EXISTED("500","EMAIL_EXISTED!"),
    CURRENT_PASSWORD_INCORRECT("007","CURRENT PASSWORD INCORRECT!"),
    ROLE_INVALID("400","ROLE DOES NOT EXIST!"),
    ACTIVE_KEY_NOT_FOUND("777","KEY ACTIVE USER NOT FOUND!"),
    ATTACHMENT_INVALID("017", "ATTACHMENT INVALID"),
    EMAIL_NOT_SENT("018", "Email apply job is not sent"),
    ;
    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
