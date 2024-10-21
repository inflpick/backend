package com.leesh.inflpick.v2.token.adapter.in.token;

import com.leesh.inflpick.v2.token.adapter.out.token.jwt.AuthProperties;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
class AuthController implements AuthControllerDocs {

    private final AuthProperties authProperties;

    @GetMapping(path = "/oauth2/authorization/{provider}")
    public ResponseEntity<Void> oauth2Authorization(@PathVariable String provider) {
        return ResponseEntity.status(HttpStatus.FOUND.value())
                .header(HttpHeaders.LOCATION, authProperties.redirectUri())
                .build();
    }

    @Hidden
    @GetMapping(path = "/oauth2/success")
    String oauth2AuthorizationSuccess() {
        return "oauth2/authorization";
    }

}
