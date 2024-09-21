package com.leesh.inflpick.influencer.port.out;

import com.leesh.inflpick.common.adapter.out.storage.InvalidFileRequestException;
import com.leesh.inflpick.common.adapter.out.storage.ThirdPartyStorageException;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String upload(@NotNull MultipartFile file, @NotNull String resourcePath) throws ThirdPartyStorageException, InvalidFileRequestException;

}
