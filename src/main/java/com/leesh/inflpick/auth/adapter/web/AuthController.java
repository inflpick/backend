package com.leesh.inflpick.auth.adapter.web;

import com.leesh.inflpick.user.adapter.in.web.TokenWebRequest;
import com.leesh.inflpick.user.adapter.in.web.TokenWebResponse;
import com.leesh.inflpick.user.v2.core.entity.User;
import com.leesh.inflpick.user.v2.core.entity.vo.AuthenticationCode;
import com.leesh.inflpick.user.port.in.UserQueryService;
import com.leesh.inflpick.user.port.out.GrantType;
import com.leesh.inflpick.user.port.out.Token;
import com.leesh.inflpick.user.port.out.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController implements AuthApiDocs {

    private final TokenService tokenService;
    private final UserQueryService userQueryService;

    @PostMapping(path = "/auth/token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenWebResponse> createToken(@RequestBody TokenWebRequest tokenWebRequest) {

        if (!tokenWebRequest.grantType().equalsIgnoreCase(GrantType.AUTHORIZATION_CODE.name())) {
            throw new IllegalArgumentException("지원하지 않는 grant type 입니다.");
        }

        AuthenticationCode authenticationCode = AuthenticationCode.create(tokenWebRequest.code());
        User user = userQueryService.query(authenticationCode);
        Token accessToken = tokenService.createAccessToken(user);
        Token refreshToken = tokenService.createRefreshToken(user);
        TokenWebResponse response = TokenWebResponse.of(accessToken, refreshToken);
        return ResponseEntity.ok()
                .body(response);
    }

}
