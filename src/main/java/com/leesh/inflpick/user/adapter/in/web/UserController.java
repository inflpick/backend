package com.leesh.inflpick.user.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.WebOffsetPageRequest;
import com.leesh.inflpick.common.adapter.in.web.exception.UnauthorizedException;
import com.leesh.inflpick.common.adapter.in.web.security.CustomUserDetails;
import com.leesh.inflpick.common.adapter.in.web.value.WebPageResponse;
import com.leesh.inflpick.common.port.PageRequest;
import com.leesh.inflpick.common.port.PageResponse;
import com.leesh.inflpick.user.core.domain.User;
import com.leesh.inflpick.user.port.in.UserQueryService;
import com.leesh.inflpick.user.port.out.TokenService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(path = "/users")
@RestController
public class UserController implements UserApiDocs {

    private final TokenService tokenService;
    private final UserQueryService userQueryService;

    @Hidden
    @GetMapping(path = "/login-failure")
    public ResponseEntity<TokenWebResponse> loginFailure() {
        throw new UnauthorizedException("로그인에 실패하였습니다.");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebPageResponse<UserWebResponse>> list(@RequestParam(name = "page", required = false, defaultValue = "0")
                                                                 Integer page,
                                                                 @RequestParam(name = "size", required = false, defaultValue = "20")
                                                                 Integer size,
                                                                 @RequestParam(name = "sort", required = false, defaultValue = "createdDate,asc")
                                                                 String[] sort) {

        PageRequest request = new WebOffsetPageRequest(page, size, sort);
        PageResponse<User> pageResponse = userQueryService.query(request);
        UserWebResponse[] webContents = pageResponse.contents().stream()
                .map(UserWebResponse::from)
                .toList()
                .toArray(UserWebResponse[]::new);
        WebPageResponse<UserWebResponse> response = WebPageResponse.of(webContents, pageResponse);
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping(path = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserWebResponse> me(@AuthenticationPrincipal UserDetails userDetails) {
        CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
        User user = customUserDetails.user();
        return ResponseEntity.ok()
                .body(UserWebResponse.from(user));
    }

}
