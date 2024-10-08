package az.company.auth_api.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends GeneralException {

    private static final long serialVersionUID = 58432132465811L;

    public ForbiddenException(JwtExceptionCodes keyAndMessage) {
        super(keyAndMessage, HttpStatus.FORBIDDEN);
    }

}