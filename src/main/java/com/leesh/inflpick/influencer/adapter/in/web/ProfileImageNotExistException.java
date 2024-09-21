package com.leesh.inflpick.influencer.adapter.in.web;

import java.text.MessageFormat;

public class ProfileImageNotExistException extends RuntimeException {

    public static MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("프로필 이미지 파일이 존재하지 않습니다.");

    public ProfileImageNotExistException(String message) {
        super(message);
    }
}
