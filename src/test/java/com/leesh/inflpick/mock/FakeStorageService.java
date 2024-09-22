package com.leesh.inflpick.mock;

import com.leesh.inflpick.common.port.out.exception.ThirdPartyStorageException;
import com.leesh.inflpick.common.port.out.StorageService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FakeStorageService implements StorageService {

    private final List<String> uploadedFiles = new ArrayList<>();

    @Override
    public String upload(@NotNull MultipartFile file, @NotNull Path resourcePath) throws ThirdPartyStorageException {
        return Paths.get(resourcePath.toString(), file.getOriginalFilename()).toString();
    }

}
