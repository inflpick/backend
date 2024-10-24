package com.leesh.inflpick.v2.token.adapter.out.token.jwt;

import com.leesh.inflpick.v2.token.adapter.out.TokenExtractor;
import com.leesh.inflpick.v2.token.domain.vo.Token;
import com.leesh.inflpick.v2.user.domain.vo.UserId;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@EnableConfigurationProperties({JwtProperties.class, AuthProperties.class})
@Component
public class JwtExtractor implements TokenExtractor {

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
}
