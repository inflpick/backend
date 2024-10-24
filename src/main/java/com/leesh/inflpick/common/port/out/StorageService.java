package com.leesh.inflpick.common.port.out;

import com.leesh.inflpick.v2.shared.application.exception.FileFormatException;
import com.leesh.inflpick.v2.shared.application.exception.ThirdPartyStorageException;
import com.leesh.inflpick.v2.shared.application.port.out.storage.StorageProperties;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URL;
import java.nio.file.Path;

public interface StorageService {

    URL upload(@NotNull MultipartFile file,
               @NotNull Path path) throws ThirdPartyStorageException, FileFormatException;

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
