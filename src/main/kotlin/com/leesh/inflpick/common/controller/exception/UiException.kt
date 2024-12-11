package com.leesh.inflpick.common.controller.exception

import com.leesh.inflpick.common.controller.ErrorCode

class UiException(errorCode: ErrorCode) : InflpickException(errorCode) {
}