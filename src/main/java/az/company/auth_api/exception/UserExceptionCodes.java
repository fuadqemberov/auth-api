package az.company.auth_api.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public enum UserExceptionCodes implements ExceptionKeyAndMessage {
   USER_NOT_FOUND("900","User not found");


    UserExceptionCodes(String exceptionKey, String exceptionMessage) {
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