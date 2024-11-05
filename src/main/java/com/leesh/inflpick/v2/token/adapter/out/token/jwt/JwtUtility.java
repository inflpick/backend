package com.leesh.inflpick.v2.token.adapter.out.token.jwt;

import com.leesh.inflpick.v2.token.adapter.out.token.jwt.vo.AuthProperties;
import com.leesh.inflpick.v2.token.adapter.out.token.jwt.vo.JwtProperties;
import com.leesh.inflpick.v2.token.application.port.out.TokenExtractorPort;
import com.leesh.inflpick.v2.token.application.port.out.TokenGeneratorPort;
import com.leesh.inflpick.v2.token.application.port.out.TokenValidatorPort;
import com.leesh.inflpick.v2.token.domain.Token;
import com.leesh.inflpick.v2.token.domain.vo.TokenType;
import com.leesh.inflpick.v2.user.domain.vo.UserId;
import io.jsonwebtoken.*;
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
public class JwtUtility implements TokenGeneratorPort, TokenExtractorPort, TokenValidatorPort {

    private final JwtProperties jwtProperties;

    @Override
    public UserId extract(Token token) {
        String userId = Jwts.parserBuilder()
                .setSigningKey(jwtProperties.secretKey().getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token.value())
                .getBody()
                .getId();
        return UserId.create(userId);
    }


    @Override
    public Token generate(UserId userId, TokenType type) {
        Integer expiresInSeconds = jwtProperties.accessTokenExpiresInSeconds();
        if (type == TokenType.REFRESH) {
            expiresInSeconds = jwtProperties.refreshTokenExpiresInSeconds();
        }
        String token = generateToken(expiresInSeconds, type, userId.id());
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


    @Override
    public Boolean isValid(Token token, TokenType tokenType) {
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
    public Boolean isExpired(Token token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtProperties.secretKey().getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(token.value());
            return false;
        } catch (ExpiredJwtException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
