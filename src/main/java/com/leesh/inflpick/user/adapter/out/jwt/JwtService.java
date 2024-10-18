package com.leesh.inflpick.user.adapter.out.jwt;

import com.leesh.inflpick.user.core.domain.User;
import com.leesh.inflpick.user.port.in.UserQueryService;
import com.leesh.inflpick.user.port.out.Token;
import com.leesh.inflpick.user.port.out.TokenService;
import com.leesh.inflpick.user.port.out.TokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
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
@EnableConfigurationProperties({JwtProperties.class, CookieProperties.class})
@Component
public class JwtService implements TokenService {

    private final JwtProperties jwtProperties;
    private final UserQueryService userQueryService;

    @Override
    public Token createAccessToken(User user) {
        Integer expiresInSeconds = jwtProperties.accessTokenExpiresInSeconds();
        String id = user.getId();
        String token = generateToken(expiresInSeconds, TokenType.ACCESS, id);
        return new Jwt(token, expiresInSeconds);
    }

    @Override
    public Token createRefreshToken(User user) {
        Integer expiresInSeconds = jwtProperties.refreshTokenExpiresInSeconds();
        String id = user.getId();
        String token = generateToken(expiresInSeconds, TokenType.REFRESH, id);
        return new Jwt(token, expiresInSeconds);
    }

    @Override
    public Boolean verifyToken(Token token, TokenType type) {
        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey(jwtProperties.secretKey().getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(token.value());
            String subject = jws.getBody().getSubject();
            return subject.equals(type.name());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User extractToken(Token token) {
        String userId = Jwts.parserBuilder()
                .setSigningKey(jwtProperties.secretKey().getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token.value())
                .getBody()
                .getSubject();
        return userQueryService.query(userId);
    }

    @Override
    public Token createAccessToken(Token refreshToken) {
        String userId = Jwts.parserBuilder()
                .setSigningKey(jwtProperties.secretKey().getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(refreshToken.value())
                .getBody()
                .getSubject();
        User user = userQueryService.query(userId);
        return createAccessToken(user);
    }


    private String generateToken(Integer expiresInSeconds, TokenType type, String id) {
        Instant now = Instant.now();
        Date expiration = Date.from(now.plusSeconds(expiresInSeconds));
        byte[] secretKeyBytes = jwtProperties.secretKey().getBytes(StandardCharsets.UTF_8);
        SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);
        return Jwts.builder()
                .setSubject(type.name())
                .setId(id)
                .setIssuedAt(Date.from(now))
                .setExpiration(expiration)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }
}
