package com.leesh.inflpick.common.controller.exception

import com.leesh.inflpick.common.controller.ErrorCode

open class InflpickException(errorCode: ErrorCode) : RuntimeException(errorCode.toErrorMessage()) {
}