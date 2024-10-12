package com.leesh.inflpick.user.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.user.port.in.UserCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "유저 API")
@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserCommandService userCommandService;

    @ApiErrorCodeSwaggerDocs(values = {Oauth2LoginApiErrorCode.class}, httpMethod = "POST", apiPath = "/users/social-login")
    @Operation(summary = "OAuth2.0 로그인", description = "OAuth2.0 로그인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공", headers = @Header(name = "Location", description = "생성된 인플루언서의 URI", schema = @Schema(type = "string"))),
    })
    @PostMapping(value = "/login/oauth2", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> socialLogin(@RequestBody Oauth2LoginRequest request) {

        Oauth2Type oauth2Type = Oauth2Type.from(request.oauth2Type());

        return ResponseEntity.ok().build();
    }


}
