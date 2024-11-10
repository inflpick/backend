package com.leesh.inflpick.v2.token.adapter.in.web;

import com.leesh.inflpick.v2.token.adapter.in.web.exception.NotSupportedGrantTypeException;
import com.leesh.inflpick.v2.token.adapter.out.docs.swagger.CreateTokenControllerDocs;
import com.leesh.inflpick.v2.token.adapter.out.token.jwt.Jwt;
import com.leesh.inflpick.v2.token.application.dto.CreateTokenRequest;
import com.leesh.inflpick.v2.token.application.dto.CreateTokenResponse;
import com.leesh.inflpick.v2.token.application.port.in.CreateTokenUseCase;
import com.leesh.inflpick.v2.token.domain.vo.GrantType;
import com.leesh.inflpick.v2.user.domain.vo.AuthenticationCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CreateTokenController implements CreateTokenControllerDocs {

    private final CreateTokenUseCase createTokenUseCase;

    @PostMapping(path = "/auth/token")
    public ResponseEntity<CreateTokenResponse> createToken(@RequestBody CreateTokenRequest request) {

        CreateTokenResponse response;

        if (request.grantType().equalsIgnoreCase(GrantType.AUTHENTICATION_CODE.name())) {
            AuthenticationCode code = AuthenticationCode.create(request.code());
            response = createTokenUseCase.create(code);
        } else if (request.grantType().equalsIgnoreCase(GrantType.REFRESH_TOKEN.name())) {
            String jwt = request.refreshToken();
            Jwt refreshToken = Jwt.create(jwt);
            response = createTokenUseCase.refresh(refreshToken);
        } else {
            throw new NotSupportedGrantTypeException(request.grantType());
        }

        return ResponseEntity.ok(response);
    }

}
