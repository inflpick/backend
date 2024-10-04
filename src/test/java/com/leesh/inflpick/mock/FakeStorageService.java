package com.leesh.inflpick.mock;

import com.leesh.inflpick.common.port.out.StorageService;
import com.leesh.inflpick.common.port.out.exception.ThirdPartyStorageException;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FakeStorageService implements StorageService {

    private final List<String> uploadedFiles = new ArrayList<>();

    @Override
    public URL upload(@NotNull MultipartFile file, @NotNull Path path) throws ThirdPartyStorageException {
        uploadedFiles.add(file.getOriginalFilename());
        return null;
    }

    @Override
    public String getUrlString(String path) {
        return "";
    }

}
