package az.company.auth_api.security;

import az.company.auth_api.service.AuthenticationService;
import az.company.auth_api.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {


private final HandlerExceptionResolver handlerExceptionResolver;
private final AuthenticationService authenticationService;
private final JwtService service;

    @Override
    protected void doFilterInternal(@NonNull  HttpServletRequest request,
                                    @NonNull  HttpServletResponse response,
                                    @NonNull  FilterChain filterChain) {

        try{
            final String jwt = service.getTokenFromRequest(request);
            if(Objects.nonNull(jwt) && authenticationService.validateToken(jwt)){
                final Claims claims = authenticationService.getClaims(jwt);
                final JwtAuthentication jwtInfoToken = service.generate(claims);
                jwtInfoToken.setAuthenticated(true);
                SecurityContextHolder.getContext().setAuthentication(jwtInfoToken);
            }

            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }


}

