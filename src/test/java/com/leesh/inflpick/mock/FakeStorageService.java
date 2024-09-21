package com.leesh.inflpick.mock;

import com.leesh.inflpick.common.adapter.out.storage.ThirdPartyStorageException;
import com.leesh.inflpick.influencer.port.out.StorageService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FakeStorageService implements StorageService {

    private final List<String> uploadedFiles = new ArrayList<>();

    @Override
    public String upload(@NotNull MultipartFile file, @NotNull String resourcePath) throws ThirdPartyStorageException {
        return Paths.get(resourcePath, file.getOriginalFilename()).toString();
    }

}
