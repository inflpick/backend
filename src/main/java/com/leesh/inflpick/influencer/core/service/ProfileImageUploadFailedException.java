package com.leesh.inflpick.influencer.core.service;

import com.leesh.inflpick.common.adapter.out.storage.ThirdPartyStorageException;

public class ProfileImageUploadFailedException extends RuntimeException {

    public ProfileImageUploadFailedException(ThirdPartyStorageException e) {
        super("외부 서비스의 오류로 인해 프로필 이미지 업로드에 실패하였습니다.", e);
    }
}
