package com.leesh.inflpick.influencer.core.service;

import com.leesh.inflpick.common.adapter.out.storage.InvalidFileRequestException;

public class InvalidProfileImageRequestException extends InvalidFileRequestException {
    public InvalidProfileImageRequestException(Throwable cause) {
        super("사용자가 업로드 요청한 프로필 이미지가 유효하지 못하여, 파일 업로드를 거부하였습니다.", cause);
    }
}
