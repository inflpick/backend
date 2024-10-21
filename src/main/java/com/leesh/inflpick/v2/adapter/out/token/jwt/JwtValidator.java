package com.leesh.inflpick.v2.adapter.out.token.jwt;

import com.leesh.inflpick.v2.appilcation.port.out.token.TokenValidator;
import com.leesh.inflpick.v2.domain.token.vo.Token;
import com.leesh.inflpick.v2.domain.token.vo.TokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@EnableConfigurationProperties({JwtProperties.class, AuthProperties.class})
@Component
public class JwtValidator implements TokenValidator {

    private final JwtProperties jwtProperties;

    @Override
    public Boolean verify(Token token, TokenType tokenType) {
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
