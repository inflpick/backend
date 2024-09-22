package com.leesh.inflpick.influencer.adapter.in.web.value;

import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorCode;
import com.leesh.inflpick.influencer.core.domain.exception.*;
import org.springframework.http.HttpStatus;

public enum InfluencerCreateApiErrorCode implements ApiErrorCode {

    INFLUENCER_NAME_VALIDATION_FAILED("IN_C_0001", HttpStatus.BAD_REQUEST, "유효한 인플루언서 이름을 입력해주세요.", InfluencerNameValidationFailedException.ERROR_MESSAGE_FORMAT.toPattern(),  "인플루언서 이름 필드가 유효성 검증에 실패한 경우에 발생합니다."),
    INFLUENCER_INTRODUCTION_VALIDATION_FAILED("IN_C_0002", HttpStatus.BAD_REQUEST, "유효한 인플루언서 소개를 입력해주세요.", InfluencerIntroductionValidationFailedException.ERROR_MESSAGE_FORMAT.toPattern(), "인플루언서 소개 필드가 유효성 검증에 실패한 경우에 발생합니다."),
    INFLUENCER_DESCRIPTION_VALIDATION_FAILED("IN_C_0003", HttpStatus.BAD_REQUEST, "유효한 인플루언서 설명을 입력해주세요.", InfluencerDescriptionValidationFailedException.ERROR_MESSAGE_FORMAT.toPattern(), "인플루언서 설명 필드가 유효성 검증에 실패한 경우에 발생합니다."),
    INVALID_SOCIAL_MEDIA_TYPE("IN_C_0004", HttpStatus.BAD_REQUEST, "유효하지 않은 소셜 미디어 타입이에요.", InvalidSocialMediaPlatformException.ERROR_MESSAGE_FORMAT.toPattern(), "소셜 미디어 타입이 유효하지 않은 경우에 발생합니다."),
    KEYWORD_MAXIMUM_SIZE_EXCEED("IN_C_0005", HttpStatus.BAD_REQUEST, "최대 키워드 수를 초과했어요.", KeywordMaximumSizeExceedException.ERROR_MESSAGE_FORMAT.toPattern(), "키워드 등록 요청이 최대 허용 개수를 초과한 경우에 발생합니다."),
    INVALID_PROFILE_IMAGE_REQUEST("IN_C_0006", HttpStatus.BAD_REQUEST, "유효하지 못한 프로필 이미지 파일을 업로드하여 서버에서 파일 업로드를 거부하였습니다.", "업로드한 이미지 파일의 이름이나, 내용을 확인 후 올바른 파일로 재요청 바랍니다.", "주로 파일의 이름이 비어있거나, 파일 내용에 잘못된 문자 형식으로 인해 발생합니다."),
    PROFILE_IMAGE_UPLOAD_FAILED("IN_C_0007", HttpStatus.INTERNAL_SERVER_ERROR, "외부 서비스의 오류로 인해 프로필 이미지 파일 업로드에 실패하였습니다.", "잠시 후 다시 요청하거나, 인플픽 관리자에게 문의 바랍니다.", "프로필 이미지 파일 업로드에 실패한 경우에 발생합니다."),
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;
    private final String comment;

    InfluencerCreateApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.action = action;
        this.comment = comment;
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public HttpStatus httpStatus() {
        return this.httpStatus;
    }

    @Override
    public String reason() {
        return this.reason;
    }

    @Override
    public String action() {
        return this.action;
    }

    @Override
    public String comment() {
        return this.comment;
    }
}
