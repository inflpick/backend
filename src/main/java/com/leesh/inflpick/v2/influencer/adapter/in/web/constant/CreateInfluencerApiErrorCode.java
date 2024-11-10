package com.leesh.inflpick.v2.influencer.adapter.in.web.constant;

import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CreateInfluencerApiErrorCode implements ApiErrorCode {

    NOT_SUPPORTED_SNS_PLATFORM("CI_0001", HttpStatus.BAD_REQUEST, "지원하지 않는 SNS 플랫폼이에요.", "SNS 플랫폼 요청 값을 확인 해주세요.", "지원하지 않는 SNS 플랫폼으로 요청 시 발생합니다."),
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;
    private final String comment;

    CreateInfluencerApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.action = action;
        this.comment = comment;
    }
}
