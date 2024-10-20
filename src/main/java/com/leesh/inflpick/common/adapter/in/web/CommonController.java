package com.leesh.inflpick.common.adapter.in.web;

import com.leesh.inflpick.v2.adapter.out.token.jwt.CookieProperties;
import com.leesh.inflpick.common.adapter.in.web.docs.CommonApiDocs;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
