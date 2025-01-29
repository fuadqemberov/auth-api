package az.company.auth_api.util;




import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DigitRangeValidator implements ConstraintValidator<DigitRange, Number> {

    private int minDigit;
    private int maxDigit;

    @Override
    public void initialize(DigitRange constraintAnnotation) {
        this.minDigit = constraintAnnotation.minDigit();
        this.maxDigit = constraintAnnotation.maxDigit();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // @NotNull ile null kontrolü yapılmalı
        }

        // Sayıyı String'e çevir (negatif işaretini ve ondalık kısmını yok say)
        String numberStr = value.toString()
                .replaceFirst("-", "")       // Negatif işaretini kaldır
                .replaceAll("\\.\\d+", "");  // Ondalık kısmı sil (örneğin 123.45 → 123)

        if (!numberStr.isEmpty() && !numberStr.equals("0")) {
            numberStr = numberStr.replaceFirst("^0+", "");
        }

        int digitCount = numberStr.length();

        return digitCount >= minDigit && digitCount <= maxDigit;
    }
}