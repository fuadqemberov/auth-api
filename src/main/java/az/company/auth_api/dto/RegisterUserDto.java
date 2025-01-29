package az.company.auth_api.dto;

import az.company.auth_api.util.DigitRange;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterUserDto {
    String email;
    String password;
    String name;
    String surname;
    @NotNull(message = "Price cannot be null")
    @DigitRange(minDigit = 2, maxDigit = 5)
    private BigDecimal price;
}
