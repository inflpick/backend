package com.leesh.inflpick.v2.adapter.out.token.jwt;

import com.leesh.inflpick.v2.application.port.out.token.TokenGenerator;
import com.leesh.inflpick.v2.domain.token.vo.Token;
import com.leesh.inflpick.v2.domain.token.vo.TokenType;
import com.leesh.inflpick.v2.domain.user.vo.UserId;
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
@EnableConfigurationProperties({JwtProperties.class, AuthProperties.class})
@Component
public class JwtGenerator implements TokenGenerator {

    private final JwtProperties jwtProperties;

    @Override
    public Token generate(UserId userId, TokenType type) {
        Integer expiresInSeconds = jwtProperties.accessTokenExpiresInSeconds();
        if (type == TokenType.REFRESH) {
            expiresInSeconds = jwtProperties.refreshTokenExpiresInSeconds();
        }
        String token = generateToken(expiresInSeconds, type, userId.getValue());
        return Jwt.create(token, expiresInSeconds);
    }

    private String generateToken(Integer expiresInSeconds, TokenType type, String userId) {
        Instant now = Instant.now();
        Date expiration = Date.from(now.plusSeconds(expiresInSeconds));
        byte[] secretKeyBytes = jwtProperties.secretKey().getBytes(StandardCharsets.UTF_8);
        SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);
        return Jwts.builder()
                .setSubject(type.name())
                .setId(userId)
                .setIssuedAt(Date.from(now))
                .setExpiration(expiration)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }
}
