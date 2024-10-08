package az.company.auth_api.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public enum JwtExceptionCodes implements ExceptionKeyAndMessage{
    JWT_ERROR("1502","jwt-error"),
    ACCESS_DENIED("1503","access-denied"),
    EXPIRED_JWT_ERROR("1504","expired-jwt-error"),
    UNSUPPORTED_JWT_ERROR("1505","unsupported-jwt-error"),
    MALFORMED_JWT_ERROR("1506","malformed-jwt-error"),
    SIGNATURE_JWT_ERROR("1507","signature-jwt-error");


    JwtExceptionCodes(String exceptionKey,String message) {
        this.exceptionKey = exceptionKey;
    }
    private final static MessageSource messageSource = ApplicationContextProvider.getApplicationContext().getBean(MessageSource.class);
    private final String exceptionKey;

    @Override
    public String getExceptionKey() {
        return exceptionKey;
    }

    @Override
    public String getExceptionMessage() {
        return messageSource.getMessage(exceptionKey, null, LocaleContextHolder.getLocale());
    }
}
