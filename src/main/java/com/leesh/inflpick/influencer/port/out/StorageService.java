package com.leesh.inflpick.influencer.port.out;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String upload(@NotNull MultipartFile file, @NotNull String basePath) throws FileUploadException;

}
