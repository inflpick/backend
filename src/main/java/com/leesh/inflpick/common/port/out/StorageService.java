package com.leesh.inflpick.common.port.out;

import com.leesh.inflpick.common.port.out.exception.InvalidFileRequestException;
import com.leesh.inflpick.common.port.out.exception.ThirdPartyStorageException;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {

    String upload(@NotNull MultipartFile file,
                  @NotNull Path resourcePath) throws ThirdPartyStorageException, InvalidFileRequestException;

}
