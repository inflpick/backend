package com.leesh.inflpick.common.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.docs.CommonApiDocs;
import com.leesh.inflpick.user.adapter.out.jwt.CookieProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommonController implements CommonApiDocs {

    private final CookieProperties cookieProperties;

    @GetMapping(path = "/api")
    public ResponseEntity<Void> common() {
        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @GetMapping(path = "/favicon.ico")
    public void favicon() {
        // favicon.ico 요청에 대한 응답을 처리합니다.
    }

    @GetMapping(path = "/oauth2/authorization/{oauth2Type}")
    public ResponseEntity<Void> oauth2Authorization(@PathVariable String oauth2Type) {
        return ResponseEntity.status(HttpStatus.FOUND.value())
                .header(HttpHeaders.LOCATION, cookieProperties.redirectUri())
                .build();
    }

    @GetMapping(path = "/oauth2/success")
    public String oauth2Authorization() {
        return "oauth2/authorization";
    }
}
