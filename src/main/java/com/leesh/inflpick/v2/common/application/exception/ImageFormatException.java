package com.leesh.inflpick.v2.common.application.exception;

public class ImageFormatException extends RuntimeException {
    public ImageFormatException(String filename) {
        super("this file is not an image type, filename : " + filename);
    }
}
