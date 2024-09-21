package com.leesh.inflpick.common.adapter.out.storage.filesystem;

import com.leesh.inflpick.influencer.port.out.StorageService;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@EnableConfigurationProperties(FileStorageProperties.class)
@Configuration
public class FileStorageService implements StorageService {

    @Override
    public String upload(@NotNull MultipartFile file, @NotNull String basePath) throws FileUploadException {
        try {
            // Create the directory if it doesn't exist
            File directory = new File(basePath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Create the file on server
            File serverFile = new File(directory.getAbsolutePath() + File.separator + file.getOriginalFilename());
            try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                stream.write(file.getBytes());
            }

            return serverFile.getAbsolutePath();
        } catch (IOException e) {
            throw new FileUploadException("Failed to upload file", e);
        }
    }
}
