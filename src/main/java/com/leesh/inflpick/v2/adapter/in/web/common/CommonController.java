package com.leesh.inflpick.v2.adapter.in.web.common;

import com.leesh.inflpick.v2.adapter.out.docs.swagger.common.CommonControllerDocs;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommonController implements CommonControllerDocs {

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
