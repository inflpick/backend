package com.leesh.inflpick.common.port.out;

import com.leesh.inflpick.common.adapter.out.storage.StorageProperties;
import com.leesh.inflpick.common.port.out.exception.InvalidFileRequestException;
import com.leesh.inflpick.common.port.out.exception.ThirdPartyStorageException;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URL;
import java.nio.file.Path;

public interface StorageService {

    URL upload(@NotNull MultipartFile file,
               @NotNull Path path) throws ThirdPartyStorageException, InvalidFileRequestException;

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
