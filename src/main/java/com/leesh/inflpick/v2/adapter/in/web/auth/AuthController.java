package com.leesh.inflpick.v2.adapter.in.web.auth;

import com.leesh.inflpick.v2.adapter.in.web.auth.dto.request.TokenWebRequest;
import com.leesh.inflpick.v2.adapter.in.web.auth.dto.response.TokenWebResponse;
import com.leesh.inflpick.v2.adapter.out.token.jwt.CookieProperties;
import com.leesh.inflpick.v2.appilcation.port.in.token.CreateTokenUseCase;
import com.leesh.inflpick.v2.appilcation.port.in.user.QueryUserUseCase;
import com.leesh.inflpick.v2.domain.token.vo.GrantType;
import com.leesh.inflpick.v2.domain.token.vo.Token;
import com.leesh.inflpick.v2.domain.token.vo.TokenType;
import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.AuthenticationCode;
import com.leesh.inflpick.v2.domain.user.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
class AuthController implements AuthControllerDocs {

    private final CookieProperties cookieProperties;
    private final CreateTokenUseCase createTokenUseCase;
    private final QueryUserUseCase queryUserUseCase;

    @GetMapping(path = "/oauth2/authorization/{oauth2Provider}")
    public ResponseEntity<Void> oauth2Authorization(@PathVariable String oauth2Provider) {
        return ResponseEntity.status(HttpStatus.FOUND.value())
                .header("Location", cookieProperties.redirectUri())
                .build();
    }

    @GetMapping(path = "/oauth2/success")
    String oauth2AuthorizationSuccess() {
        return "oauth2/authorization";
    }

    @PostMapping(path = "/auth/token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenWebResponse> createToken(@RequestBody TokenWebRequest request) {

        if (!request.grantType().equalsIgnoreCase(GrantType.AUTHENTICATION_CODE.name())) {
            throw new IllegalArgumentException("지원하지 않는 grant type 입니다.");
        }

        String code = request.code();
        AuthenticationCode authenticationCode = AuthenticationCode.create(code);
        User user = queryUserUseCase.query(authenticationCode);
        UserId userId = user.getId();
        Token accessToken = createTokenUseCase.create(userId, TokenType.ACCESS);
        Token refreshToken = createTokenUseCase.create(userId, TokenType.REFRESH);
        TokenWebResponse response = TokenWebResponse.create(accessToken, refreshToken);
        return ResponseEntity.ok()
                .body(response);
    }

}
