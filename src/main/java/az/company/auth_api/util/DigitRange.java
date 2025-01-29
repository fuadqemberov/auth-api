package az.company.auth_api.util;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DigitRangeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DigitRange {
    String message() default "Number must have between {minDigit} and {maxDigit} digits";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int minDigit() default 1;    // Varsayılan: en az 1 basamak
    int maxDigit() default 10;   // Varsayılan: en fazla 10 basamak
}