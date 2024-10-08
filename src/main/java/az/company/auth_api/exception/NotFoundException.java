package az.company.auth_api.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends GeneralException {

    private static final long serialVersionUID = 58432132465811L;

    public NotFoundException(UserExceptionCodes keyAndMessage) {
        super(keyAndMessage, HttpStatus.NOT_FOUND);
    }

    public NotFoundException(String message,
                             Enum<? extends ExceptionKeyAndMessage> key) {
        super(message, key, HttpStatus.NOT_FOUND);
    }

    public NotFoundException(String message,
                             String key) {
        super(message, key, HttpStatus.BAD_REQUEST);
    }
}