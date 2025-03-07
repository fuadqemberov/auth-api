package az.company.auth_api.dto;

import jakarta.validation.constraints.Digits;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    String name;
    String surname;
    String email;
    BigDecimal price;
}
