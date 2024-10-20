package com.leesh.inflpick.v2.adapter.out.token.jwt.auth;

import com.leesh.inflpick.v2.appilcation.port.in.auth.TokenUseCase;
import com.leesh.inflpick.v2.appilcation.port.in.user.GetUserUseCase;
import com.leesh.inflpick.v2.domain.auth.vo.Token;
import com.leesh.inflpick.v2.domain.auth.vo.TokenType;
import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.UserId;
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
public class JwtService implements TokenUseCase {

    private final JwtProperties jwtProperties;
    private final GetUserUseCase userUseCase;

    @Override
    public Token createAccessToken(UserId userId) {
        Integer expiresInSeconds = jwtProperties.accessTokenExpiresInSeconds();
        String token = generateToken(expiresInSeconds, TokenType.ACCESS, userId.value());
        return new Jwt(token, expiresInSeconds);
    }

    @Override
    public Token createRefreshToken(UserId userId) {
        Integer expiresInSeconds = jwtProperties.refreshTokenExpiresInSeconds();
        String token = generateToken(expiresInSeconds, TokenType.REFRESH, userId.value());
        return new Jwt(token, expiresInSeconds);
    }

    @Override
    public boolean verifyToken(Token token, TokenType tokenType) {
        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey(jwtProperties.secretKey().getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(token.value());
            String subject = jws.getBody().getSubject();
            return subject.equals(tokenType.name());
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
        return userUseCase.getUser(UserId.create(userId));
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
