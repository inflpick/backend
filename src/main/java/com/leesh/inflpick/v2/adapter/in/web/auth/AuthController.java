package com.leesh.inflpick.v2.adapter.in.web.auth;

import com.leesh.inflpick.v2.adapter.out.token.jwt.auth.CookieProperties;
import com.leesh.inflpick.v2.domain.auth.vo.GrantType;
import com.leesh.inflpick.v2.domain.auth.vo.Token;
import com.leesh.inflpick.v2.appilcation.port.in.auth.TokenUseCase;
import com.leesh.inflpick.v2.adapter.in.web.auth.docs.swagger.AuthControllerDocs;
import com.leesh.inflpick.v2.adapter.in.web.auth.dto.request.TokenWebRequest;
import com.leesh.inflpick.v2.adapter.in.web.auth.dto.response.TokenWebResponse;
import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.AuthenticationCode;
import com.leesh.inflpick.v2.domain.user.vo.UserId;
import com.leesh.inflpick.v2.appilcation.port.in.user.GetUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController implements AuthControllerDocs {

    private final CookieProperties cookieProperties;
    private final TokenUseCase tokenUseCase;
    private final GetUserUseCase getUserUseCase;

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

    @GetMapping(path = "/auth/token", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TokenWebResponse> createToken(@RequestBody TokenWebRequest request) {

        if (!request.grantType().equalsIgnoreCase(GrantType.AUTHORIZATION_CODE.name())) {
            throw new IllegalArgumentException("지원하지 않는 grant type 입니다.");
        }

        String code = request.code();
        AuthenticationCode authenticationCode = AuthenticationCode.create(code);
        User user = getUserUseCase.getUser(authenticationCode);
        UserId userId = user.getId();
        Token accessToken = tokenUseCase.createAccessToken(userId);
        Token refreshToken = tokenUseCase.createRefreshToken(userId);
        TokenWebResponse response = TokenWebResponse.create(accessToken, refreshToken);
        return ResponseEntity.ok()
                .body(response);
    }

}
