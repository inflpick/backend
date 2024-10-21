package com.leesh.inflpick.v2.adapter.in.web.common.error;

import com.leesh.inflpick.common.adapter.in.web.exception.InvalidSortParameterException;
import com.leesh.inflpick.common.port.in.exception.InvalidDirectionException;
import com.leesh.inflpick.common.port.in.exception.InvalidPageNumberException;
import com.leesh.inflpick.common.port.in.exception.InvalidPageSizeException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CommonApiErrorCode implements ApiErrorCode {

    SERVER_ERROR("C0001", HttpStatus.INTERNAL_SERVER_ERROR, "현재 서버가 요청을 처리할 수 없는 상태에요.", "잠시 후 다시 시도하시거나, 인플픽 관리자(info@inflpick.com)에게 문의바랍니다.", "예기치 못한 에러로 인해 서버가 요청을 처리할 수 없는 경우에 발생합니다."),
    TOO_MANY_REQUESTS("C0002", HttpStatus.TOO_MANY_REQUESTS, "요청이 너무 많아요.", "잠시 후 다시 시도하시거나, 인플픽 관리자(info@inflpick.com)에게 문의바랍니다.", "짧은 시간 동안 많은 API 요청되는 경우에 발생합니다."),
    SERVICE_UNAVAILABLE("C0003", HttpStatus.SERVICE_UNAVAILABLE, "서버가 임시 점검 중이에요.", "서버 점검이 끝난 후, 다시 시도해주세요.", "현재 서버가 점검 중이거나 서비스를 제공할 수 없는 상태인 경우에 발생합니다."),
    MISSING_REQUIRED_FIELDS("C0004", HttpStatus.BAD_REQUEST, "필수 입력 값이 입력되지 않았어요.", "필수 입력 값을 입력 후 다시 요청해주세요.", "API 요청 시, 필수 입력 값이 입력되지 않은 경우에 발생합니다."),
    RESOURCE_NOT_FOUND("C0005", HttpStatus.NOT_FOUND, "리소스를 찾을 수 없어요.", "요청 ID를 확인 후 다시 시도해주세요.", "해당 ID를 가진 리소스를 찾을 수 없는 경우에 발생합니다."),
    INVALID_REQUEST_BODY("C0006", HttpStatus.BAD_REQUEST, "요청 본문이 올바르지 않아요.", "요청 본문을 확인 후 다시 요청해주세요.", "API 요청 본문이 올바르지 않은 경우에 발생합니다. (ex. Json 형식이 올바르지 않은 경우)"),
    UNSUPPORTED_HTTP_METHOD("C0007", HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 메소드에요.", "요청을 확인 후 다시 요청해주세요.", "API 요청 시, 지원하지 않는 HTTP 메소드를 사용한 경우에 발생합니다."),
    UNSUPPORTED_HTTP_MEDIA_TYPE("C0008", HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 미디어 타입이에요.", "요청을 확인 후 다시 요청해주세요.", "API 요청 시, 지원하지 않는 HTTP 미디어 타입을 사용한 경우에 발생합니다."),
    MISSING_REQUEST_PART("C0009", HttpStatus.BAD_REQUEST, "필수 입력 값이 입력되지 않았어요.", "필수 입력 값을 확인 후, 다시 시도해주세요.", "API 요청 시, 필수 파라미터가 입력되지 않은 경우에 발생합니다."),
    NOT_IMAGE_TYPE("C0010", HttpStatus.BAD_REQUEST, "이미지 파일이 아니에요.", "업로드 한 파일을 확인 후, 이미지 파일로 다시 시도해주세요.", "이미지 파일이 아닌 파일을 업로드한 경우에 발생합니다."),
    INVALID_PAGE_VALUE("C0011", HttpStatus.BAD_REQUEST, "유효한 페이지 번호를 입력해주세요.", InvalidPageNumberException.ERROR_MESSAGE_FORMAT.toPattern(), "페이지 번호가 올바르지 않은 경우에 발생합니다."),
    INVALID_PAGE_SIZE_VALUE("C0012", HttpStatus.BAD_REQUEST, "유효한 페이지 크기를 입력해주세요", InvalidPageSizeException.ERROR_MESSAGE_FORMAT.toPattern(), "페이지 크기가 올바르지 않은 경우에 발생합니다."),
    INVALID_SORT_DIRECTION("C0013", HttpStatus.BAD_REQUEST, "유효한 정렬 방향을 입력해주세요.", InvalidDirectionException.ERROR_MESSAGE_FORMAT.toPattern(), "정렬 방향이 올바르지 않은 경우에 발생합니다."),
    INVALID_SORT_PARAMETER("C0014", HttpStatus.BAD_REQUEST, "유효한 정렬 파라미터를 입력해주세요.", InvalidSortParameterException.ERROR_MESSAGE_FORMAT, "정렬 파라미터가 올바르지 않은 경우에 발생합니다."),
    NOT_FOUND_API_URL("C0015", HttpStatus.NOT_FOUND, "잘못된 API URL 이에요.", "API URL을 확인 후 다시 시도해주세요.", "존재하지 않는 API URL을 호출하는 경우에 발생합니다."),
    UNAUTHORIZED("C0016", HttpStatus.UNAUTHORIZED, "인증되지 않았어요.", "로그인 후 다시 시도해주세요.", "접근 토큰 없이 API 요청을 하는 경우에 발생합니다."),
    FORBIDDEN("C0017", HttpStatus.FORBIDDEN, "접근 권한이 없어요.", "권한을 확인 후 다시 시도해주세요.", "접근 토큰 없이 API 요청을 하거나, 요청한 리소스에 대한 권한이 없는 경우에 발생합니다."),
    EXPIRED_TOKEN("C0018", HttpStatus.UNAUTHORIZED, "접근 토큰이 만료됐어요.", "다시 로그인하거나, 문제가 반복될 경우 인플픽 관리자(info@inflpick.com)에게 문의바랍니다.", "접근 토큰이 만료된 경우 발생하는 에러로, 에러 코드를 통해 토큰 갱신 요청이 필요합니다."),
    INVALID_TOKEN("C0019", HttpStatus.UNAUTHORIZED, "유효하지 않은 접근 토큰이에요.", "다시 로그인하거나, 문제가 반복될 경우 인플픽 관리자(info@inflpick.com)에게 문의바랍니다.", "유효하지 않은 접근 토큰을 사용하여 API 요청한 경우에 발생합니다."),
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
