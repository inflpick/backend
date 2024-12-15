package com.leesh.inflpick.common.exception

open class InflpickException(errorCode: ErrorCode) : RuntimeException(errorCode.toErrorMessage()) {
}