package com.leesh.inflpick.common.adapter.in.web.value;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CommonApiErrorCode implements ApiErrorCode {

    SERVER_ERROR("C_O_0001", HttpStatus.INTERNAL_SERVER_ERROR, "현재 서버가 요청을 처리할 수 없는 상태에요.", "잠시 후 다시 시도하시거나, 인플픽 관리자에게 문의바랍니다.", "예기치 못한 에러로 인해 서버가 요청을 처리할 수 없는 경우에 발생합니다."),
    TOO_MANY_REQUESTS("C_O_0002", HttpStatus.TOO_MANY_REQUESTS, "요청이 너무 많아요.", "잠시 후 다시 시도하시거나, 인플픽 관리자에게 문의바랍니다.", "짧은 시간 동안 많은 API 요청되는 경우에 발생합니다."),
    SERVICE_UNAVAILABLE("C_O_0003", HttpStatus.SERVICE_UNAVAILABLE, "서버가 임시 점검 중이에요.", "서버 점검이 끝난 후, 다시 시도해주세요.", "현재 서버가 점검 중이거나 서비스를 제공할 수 없는 상태인 경우에 발생합니다."),
    MISSING_REQUIRED_FIELDS("C_O_0004", HttpStatus.BAD_REQUEST, "필수 입력 값이 입력되지 않았어요.", "필수 입력 값을 입력 후 다시 요청해주세요.", "API 요청 시, 필수 입력 값이 입력되지 않은 경우에 발생합니다."),
    RESOURCE_NOT_FOUND("C_O_0005", HttpStatus.NOT_FOUND, "리소스를 찾을 수 없어요.", "요청 UUID를 확인 후 다시 시도해주세요.", "해당 UUID를 가진 리소스를 찾을 수 없는 경우에 발생합니다."),
    INVALID_REQUEST_BODY("C_O_0006", HttpStatus.BAD_REQUEST, "요청 본문이 올바르지 않아요.", "요청 본문을 확인 후 다시 요청해주세요.", "API 요청 본문이 올바르지 않은 경우에 발생합니다. (ex. Json 형식이 올바르지 않은 경우)"),
    UNSUPPORTED_HTTP_METHOD("C_O_0007", HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 메소드에요.", "요청을 확인 후 다시 요청해주세요.", "API 요청 시, 지원하지 않는 HTTP 메소드를 사용한 경우에 발생합니다."),
    UNSUPPORTED_HTTP_MEDIA_TYPE("C_O_0008", HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 미디어 타입이에요.", "요청을 확인 후 다시 요청해주세요.", "API 요청 시, 지원하지 않는 HTTP 미디어 타입을 사용한 경우에 발생합니다."),
    MISSING_REQUEST_PART("C_O_0009", HttpStatus.BAD_REQUEST, "필수 입력 값이 입력되지 않았어요.", "필수 입력 값을 확인 후, 다시 시도해주세요.", "API 요청 시, 필수 파라미터가 입력되지 않은 경우에 발생합니다."),
    NOT_IMAGE_TYPE("C_O_0010", HttpStatus.BAD_REQUEST, "이미지 파일이 아니에요.", "업로드 한 파일을 확인 후, 이미지 파일로 다시 시도해주세요.", "이미지 파일이 아닌 파일을 업로드한 경우에 발생합니다."),
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

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public HttpStatus httpStatus() {
        return this.httpStatus;
    }
}
