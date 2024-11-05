package com.leesh.inflpick.v2.influencer.adapter.in.web.controller.constant;

import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum GetInfluencerApiErrorCode implements ApiErrorCode {

    NOT_FOUND("GI_0001", HttpStatus.NOT_FOUND, "조회할 인플루언서를 찾을 수 없어요.", "인플루언서 ID 값을 확인 해주세요.", "해당 인플루언서를 찾을 수 없을 때 발생합니다."),
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;
    private final String comment;

    GetInfluencerApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.action = action;
        this.comment = comment;
    }
}
