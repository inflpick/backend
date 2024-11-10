package com.leesh.inflpick.v2.common.adapter.in.web.constant;

import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CommonApiErrorCode implements ApiErrorCode {

    SERVER_ERROR("C_0001", HttpStatus.INTERNAL_SERVER_ERROR, "현재 서버가 요청을 처리할 수 없는 상태에요.", "잠시 후 다시 시도하시거나, 인플픽 관리자(info@inflpick.com)에게 문의바랍니다.", "예기치 못한 에러로 인해 서버가 요청을 처리할 수 없는 경우에 발생합니다."),
    TOO_MANY_REQUESTS("C_0002", HttpStatus.TOO_MANY_REQUESTS, "요청이 너무 많아요.", "잠시 후 다시 시도하시거나, 인플픽 관리자(info@inflpick.com)에게 문의바랍니다.", "짧은 시간 동안 많은 API 요청되는 경우에 발생합니다."),
    SERVICE_UNAVAILABLE("C_0003", HttpStatus.SERVICE_UNAVAILABLE, "서버가 임시 점검 중이에요.", "서버 점검이 끝난 후, 다시 시도해주세요.", "현재 서버가 점검 중이거나 서비스를 제공할 수 없는 상태인 경우에 발생합니다."),
    MISSING_REQUIRED_FIELDS("C_0004", HttpStatus.BAD_REQUEST, "필수 입력 값이 입력되지 않았어요.", "필수 입력 값을 입력 후 다시 요청해주세요.", "API 요청 시, 필수 입력 값이 입력되지 않은 경우에 발생합니다."),
    INVALID_REQUEST_BODY("C_0005", HttpStatus.BAD_REQUEST, "요청 본문이 올바르지 않아요.", "요청 본문을 확인 후 다시 요청해주세요.", "API 요청 본문이 올바르지 않은 경우에 발생합니다. (ex. Json 형식이 올바르지 않은 경우)"),
    UNSUPPORTED_HTTP_METHOD("C_0006", HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 메소드에요.", "요청을 확인 후 다시 요청해주세요.", "API 요청 시, 지원하지 않는 HTTP 메소드를 사용한 경우에 발생합니다."),
    UNSUPPORTED_HTTP_MEDIA_TYPE("C_0007", HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 미디어 타입이에요.", "요청을 확인 후 다시 요청해주세요.", "API 요청 시, 지원하지 않는 HTTP 미디어 타입을 사용한 경우에 발생합니다."),
    MISSING_REQUEST_PART("C_0008", HttpStatus.BAD_REQUEST, "필수 입력 값이 입력되지 않았어요.", "필수 입력 값을 확인 후, 다시 시도해주세요.", "API 요청 시, 필수 파라미터가 입력되지 않은 경우에 발생합니다."),
    NOT_ADMIN_USER("C_0009", HttpStatus.FORBIDDEN, "접근 권한이 없어요.", "관리자 권한을 확인 후 다시 시도해주세요.", "관리자 권한이 없는 사용자가 관리자 권한이 필요한 API 요청을 하는 경우에 발생합니다."),
    EXPIRED_TOKEN("C_0010", HttpStatus.UNAUTHORIZED, "접근 토큰이 만료됐어요.", "다시 로그인하거나, 문제가 반복될 경우 인플픽 관리자(info@inflpick.com)에게 문의바랍니다.", "접근 토큰이 만료된 경우 발생하는 에러로, 에러 코드를 통해 토큰 갱신 요청이 필요합니다."),
    INVALID_TOKEN("C_0011", HttpStatus.UNAUTHORIZED, "유효하지 않은 접근 토큰이에요.", "다시 로그인 해주세요.", "유효하지 않은 접근 토큰을 사용하여 API 요청한 경우에 발생합니다."),
    UNAUTHORIZED("C_0012", HttpStatus.UNAUTHORIZED, "로그인이 필요해요.", "로그인 후 다시 시도해주세요.", "접근 토큰 없이 인증이 필요한 API 요청을 하는 경우에 발생합니다."),
    FILE_UPLOAD_FAILED("C_0013", HttpStatus.SERVICE_UNAVAILABLE, "외부 서비스의 오류로 인해 파일 업로드에 실패하였습니다.", "잠시 후 다시 요청하거나, 인플픽 관리자(info@inflpick.com)에게 문의 바랍니다.", "외부 서비스와의 연동 오류로 인해 파일 업로드에 실패한 경우에 발생합니다."),
    NOT_IMAGE_TYPE("C_0014", HttpStatus.BAD_REQUEST, "이미지 파일이 아니에요.", "이미지 파일을 확인 후 다시 요청해주세요.", "이미지 파일이 아닌 파일을 업로드한 경우에 발생합니다."),
    ;

    CommonApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.action = action;
        this.comment = comment;
    }

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;
    private final String comment;

    @Override
    public String getReason() {
        return this.reason;
    }

    @Override
    public String getAction() {
        return this.action;
    }

    @Override
    public String getComment() {
        return this.comment;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
