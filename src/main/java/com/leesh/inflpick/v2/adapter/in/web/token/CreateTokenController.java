package com.leesh.inflpick.v2.adapter.in.web.token;

import com.leesh.inflpick.v2.adapter.in.web.token.dto.request.CreateTokenWebRequest;
import com.leesh.inflpick.v2.adapter.in.web.token.dto.response.CreateTokenWebResponse;
import com.leesh.inflpick.v2.adapter.in.web.token.exception.NotSupportedGrantTypeException;
import com.leesh.inflpick.v2.adapter.out.docs.swagger.auth.CreateTokenControllerDocs;
import com.leesh.inflpick.v2.adapter.out.token.jwt.Jwt;
import com.leesh.inflpick.v2.appilcation.dto.token.TokenResponse;
import com.leesh.inflpick.v2.appilcation.dto.user.AuthenticationCodeTokenRequest;
import com.leesh.inflpick.v2.appilcation.port.in.token.CreateTokenUseCase;
import com.leesh.inflpick.v2.appilcation.port.in.token.RefreshTokenUseCase;
import com.leesh.inflpick.v2.domain.token.vo.GrantType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CreateTokenController implements CreateTokenControllerDocs {

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
