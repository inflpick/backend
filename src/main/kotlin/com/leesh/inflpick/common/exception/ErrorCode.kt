package com.leesh.inflpick.common.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(val status: Int, val code: String, val reason: String, val comment: String, val action: String) {

    MISSING_REQUIRED_PARAMETER(HttpStatus.BAD_REQUEST.value(), "MISSING_REQUIRED_PARAMETER", "API 요청 필수 파라미터가 입력되지 않았습니다.", "API 요청 필수 파라미터가 입력되지 않은 경우에 발생", "API 요청 필수 파라미터 입력 후 재시도"),
    ;

    fun toErrorMessage(): String {
        return """
                  ErrorCode = [$code]
                  Reason = $reason
                  Comment = $comment
                  Action = $action
               """
    }
}