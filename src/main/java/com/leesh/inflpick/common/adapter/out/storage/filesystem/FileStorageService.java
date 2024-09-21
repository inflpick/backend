package com.leesh.inflpick.common.adapter.out.storage.filesystem;

import com.leesh.inflpick.common.adapter.out.storage.InvalidFileRequestException;
import com.leesh.inflpick.common.adapter.out.storage.ThirdPartyStorageException;
import com.leesh.inflpick.influencer.port.out.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@EnableConfigurationProperties(FileStorageProperties.class)
@Configuration
public class FileStorageService implements StorageService {

    private final String basePath;

    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.basePath = fileStorageProperties.basePath();
    }

    @Override
    public String upload(@NotNull MultipartFile file, @NotNull String resourcePath) throws ThirdPartyStorageException, InvalidFileRequestException {
        try {
            // Create the directory if it doesn't exist
            Path baseDirectory = Paths.get(basePath + resourcePath);
            if (!Files.exists(baseDirectory)) {
                Files.createDirectories(baseDirectory);
            }
            if (file.getOriginalFilename() == null) {
                throw new InvalidFileRequestException("유저가 업로드 한 파일의 이름이 존재하지 않아서 업로드 할 수 없습니다.");
            }
            Path filePath = baseDirectory.resolve(file.getOriginalFilename());
            Files.write(filePath, file.getBytes());
            return resourcePath;
        } catch (IOException e) {
            throw new InvalidFileRequestException("유저가 업로드한 파일이 유효하지 못해 업로드 할 수 없습니다.", e);
        }
    }
}
