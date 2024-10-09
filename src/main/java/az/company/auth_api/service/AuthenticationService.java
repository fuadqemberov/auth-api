package az.company.auth_api.service;

import az.company.auth_api.dto.AuthResponseDto;
import az.company.auth_api.dto.JwtCredentials;
import az.company.auth_api.dto.LoginUserDto;
import az.company.auth_api.dto.RegisterUserDto;
import az.company.auth_api.dto.TokenResponse;
import az.company.auth_api.dto.UserDto;
import az.company.auth_api.entity.RefreshToken;
import az.company.auth_api.entity.RoleName;
import az.company.auth_api.entity.User;
import az.company.auth_api.exception.JwtExceptionCodes;
import az.company.auth_api.exception.ForbiddenException;
import az.company.auth_api.exception.NotFoundException;
import az.company.auth_api.mapper.UserMapper;
import az.company.auth_api.repository.RefreshTokenRepository;
import az.company.auth_api.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final ModelMapper mapper;

    @Value("${security.jwt.secret}")
    private String jwtSecret;

    @Transactional
    public UserDto signup(RegisterUserDto input) {
        User user = User.builder()
                .email(input.getEmail())
                .name(input.getName())
                .surname(input.getSurname())
                .role(RoleName.ROLE_USER)
                .password(passwordEncoder.encode(input.getPassword()))
                .build();

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public TokenResponse authenticate(LoginUserDto input) {
        User user = userRepository.findByEmail(input.getEmail()).orElseThrow(() -> new NotFoundException("User not found", "404"));
        return jwtService.issueToken(user);
    }

    public TokenResponse refreshToken() {
        String username = getUsername();
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("User not found", "404"));

        return jwtService.issueToken(user);
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;

        } catch (ExpiredJwtException expEx) {
            //log.warn("Token expired", expEx);
            throw new ForbiddenException(JwtExceptionCodes.EXPIRED_JWT_ERROR);

        } catch (UnsupportedJwtException unsEx) {
            //log.warn("Unsupported jwt", unsEx);
            throw new ForbiddenException(JwtExceptionCodes.UNSUPPORTED_JWT_ERROR);

        } catch (MalformedJwtException mjEx) {
            //log.warn("Malformed jwt", mjEx);
            throw new ForbiddenException(JwtExceptionCodes.MALFORMED_JWT_ERROR);

        } catch (JwtException e) {
            //log.warn("Invalid token", e);
            throw new ForbiddenException(JwtExceptionCodes.JWT_ERROR);
        }
    }

    public Claims getClaims(@NonNull String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    public String getUsername() {
        return getCurrentJwtCredentials().getEmail();
    }

    public JwtCredentials getCurrentJwtCredentials() {
        var securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> mapper.map(authentication.getCredentials(), JwtCredentials.class))
                .orElseThrow(() -> new RuntimeException("JwtExceptionCodes.UNAUTHORIZED"));
    }

}

