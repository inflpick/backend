package com.leesh.inflpick.v2.adapter.in.web.auth;

import com.leesh.inflpick.v2.adapter.in.web.auth.dto.request.CreateTokenWebRequest;
import com.leesh.inflpick.v2.adapter.in.web.auth.dto.response.TokenWebResponse;
import com.leesh.inflpick.v2.adapter.in.web.auth.exception.CreateTokenFailedException;
import com.leesh.inflpick.v2.adapter.out.docs.swagger.auth.CreateTokenControllerDocs;
import com.leesh.inflpick.v2.appilcation.dto.user.AuthenticationCodeTokenRequest;
import com.leesh.inflpick.v2.appilcation.dto.user.TokenResponse;
import com.leesh.inflpick.v2.appilcation.port.in.token.CreateTokenUseCase;
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

    @Override
    @PostMapping(path = "/auth/token")
    public ResponseEntity<TokenWebResponse> createToken(@RequestBody CreateTokenWebRequest webRequest) {

        if (webRequest.grantType().equalsIgnoreCase(GrantType.AUTHENTICATION_CODE.name())) {
            AuthenticationCodeTokenRequest request = AuthenticationCodeTokenRequest.create(webRequest.code());
            TokenResponse response = createTokenUseCase.create(request);
            TokenWebResponse webResponse = TokenWebResponse.create(response);
            return ResponseEntity.ok(webResponse);
        } else if (webRequest.grantType().equalsIgnoreCase(GrantType.REFRESH_TOKEN.name())) {
            // TODO: refresh token
        }

        throw new CreateTokenFailedException("Failed to create token");
    }

}
