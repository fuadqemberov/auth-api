package az.company.auth_api.service;

import az.company.auth_api.security.JwtAuthentication;
import az.company.auth_api.dto.TokenResponse;
import az.company.auth_api.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Service
public class JwtService {

    public Key key;

    @Value("${security.jwt.secret}")
    public String secretKey;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenResponse issueToken(User user) {

        var accessToken = Jwts.builder()
                .setId(user.getId().toString())
                .setSubject(user.getEmail())
                .setHeader(Map.of("type", "JWT", "tokenType", "ACCESS"))
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(Duration.ofHours(24))))
                .signWith(key)
                .compact();

        var refreshToken = Jwts.builder()
                .setSubject(user.getEmail())
                .setHeader(Map.of("type", "JWT", "tokenType", "REFRESH"))
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(Duration.ofHours(26))))
                .signWith(key)
                .compact();

        return TokenResponse.builder()
                .userId(user.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();


    }

    public String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        if (Objects.isNull(authHeader) || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.substring(7);
    }

    public  JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setFirstName(claims.get("firstName", String.class));
        jwtInfoToken.setUsername(claims.getSubject());
        return jwtInfoToken;
    }



}
