package az.company.auth_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TokenResponse {
    private final String type = "Bearer";
    private long userId;
    private String accessToken;
    private String refreshToken;
}