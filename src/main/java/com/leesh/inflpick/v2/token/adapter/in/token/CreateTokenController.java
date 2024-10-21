package com.leesh.inflpick.v2.token.adapter.in.token;

import com.leesh.inflpick.v2.token.application.dto.TokenResponse;
import com.leesh.inflpick.v2.token.application.port.in.CreateTokenUseCase;
import com.leesh.inflpick.v2.token.application.port.in.RefreshTokenUseCase;
import com.leesh.inflpick.v2.token.domain.vo.GrantType;
import com.leesh.inflpick.v2.token.adapter.out.token.jwt.Jwt;
import com.leesh.inflpick.v2.token.application.dto.AuthenticationCodeTokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
class CreateTokenController implements CreateTokenControllerDocs {

    private final CreateTokenUseCase createTokenUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;

    @Override
    @PostMapping(path = "/auth/token")
    public ResponseEntity<CreateTokenWebResponse> createToken(@RequestBody CreateTokenWebRequest webRequest) {

        TokenResponse response;

        if (webRequest.grantType().equalsIgnoreCase(GrantType.AUTHENTICATION_CODE.name())) {
            AuthenticationCodeTokenRequest request = AuthenticationCodeTokenRequest.create(webRequest.code());
            response = createTokenUseCase.create(request);
        } else if (webRequest.grantType().equalsIgnoreCase(GrantType.REFRESH_TOKEN.name())) {
            String jwt = webRequest.refreshToken();
            Jwt refreshToken = Jwt.create(jwt);
            response = refreshTokenUseCase.refresh(refreshToken);
        } else {
            throw new NotSupportedGrantTypeException("Not supported grant type, grantType: " + webRequest.grantType());
        }

        CreateTokenWebResponse webResponse = CreateTokenWebResponse.create(response);
        return ResponseEntity.ok(webResponse);
    }

}
