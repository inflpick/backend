package com.leesh.inflpick.v2.common.application.port.out.storage;

import com.leesh.inflpick.v2.common.application.exception.FileFormatException;
import com.leesh.inflpick.v2.common.application.exception.ThirdPartyStorageException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URL;
import java.nio.file.Path;

public interface StoragePort {

    URL upload(MultipartFile profileImage, Path basePath) throws FileFormatException, ThirdPartyStorageException;

    String getUrlString(String path);

    default String getUrlString(String path, StorageProperties storageProperties) {
        if (path.isBlank()) {
            return "";
        }
        return UriComponentsBuilder.newInstance()
                .scheme(storageProperties.uri().scheme())
                .host(storageProperties.uri().host())
                .path(path)
                .build()
                .toUriString();
    }
}
