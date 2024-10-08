package az.company.auth_api.controller;

import az.company.auth_api.dto.AuthResponseDto;
import az.company.auth_api.dto.LoginUserDto;
import az.company.auth_api.dto.RefreshTokenRequest;
import az.company.auth_api.dto.RegisterUserDto;
import az.company.auth_api.dto.TokenResponse;
import az.company.auth_api.dto.UserDto;
import az.company.auth_api.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterUserDto request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody LoginUserDto request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authenticationService.refreshToken());
    }
}
