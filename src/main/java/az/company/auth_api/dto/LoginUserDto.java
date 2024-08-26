package az.company.auth_api.dto;

import lombok.Data;

@Data
public class LoginUserDto {
    private String email;
    private String password;

}