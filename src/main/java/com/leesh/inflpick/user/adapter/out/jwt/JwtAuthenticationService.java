package com.leesh.inflpick.user.adapter.out.jwt;

import com.leesh.inflpick.user.core.domain.User;
import com.leesh.inflpick.user.port.in.UserQueryService;
import com.leesh.inflpick.user.port.out.AuthenticationToken;
import com.leesh.inflpick.user.port.out.AuthenticationTokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@RequiredArgsConstructor
@EnableConfigurationProperties({JwtProperties.class})
@Component
public class JwtAuthenticationService implements AuthenticationTokenService {

    private final JwtProperties jwtProperties;
    private final UserQueryService userQueryService;

    @Override
    public AuthenticationToken createAccessToken(User user) {
        Integer expiresInSeconds = jwtProperties.accessTokenExpiresInSeconds();
        String token = generateToken(expiresInSeconds, user);
        return new JwtAuthentication(token, expiresInSeconds);
    }

    @Override
    public AuthenticationToken createRefreshToken(User user) {
        Integer expiresInSeconds = jwtProperties.refreshTokenExpiresInSeconds();
        String token = generateToken(expiresInSeconds, user);
        return new JwtAuthentication(token, expiresInSeconds);
    }

    @Override
    public Boolean verifyToken(AuthenticationToken token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtProperties.secretKey().getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(token.value());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User extractToken(JwtAuthentication jwtAuthentication) {
        String userId = Jwts.parserBuilder()
                .setSigningKey(jwtProperties.secretKey().getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(jwtAuthentication.value())
                .getBody()
                .getSubject();
        return userQueryService.getById(userId);
    }


    private String generateToken(Integer expiresInSeconds, User user) {
        Instant now = Instant.now();
        Date expiration = Date.from(now.plusSeconds(expiresInSeconds));
        byte[] secretKeyBytes = jwtProperties.secretKey().getBytes(StandardCharsets.UTF_8);
        SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);
        return Jwts.builder()
                .setSubject(user.getId())
                .setIssuedAt(Date.from(now))
                .setExpiration(expiration)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }
}
