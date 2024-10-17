package com.leesh.inflpick.common.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.docs.CommonApiDocs;
import com.leesh.inflpick.user.adapter.in.web.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController implements CommonApiDocs {

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
    public ResponseEntity<LoginResponse> oauth2Authorization(@PathVariable String oauth2Type) {
        return ResponseEntity.ok().build();
    }
}
