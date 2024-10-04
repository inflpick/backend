package com.leesh.inflpick.common.adapter.out.storage.aws;

import com.leesh.inflpick.common.adapter.out.storage.StorageProperties;
import com.leesh.inflpick.common.port.out.StorageService;
import com.leesh.inflpick.common.port.out.exception.InvalidFileRequestException;
import com.leesh.inflpick.common.port.out.exception.ThirdPartyStorageException;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

@Profile("prod")
@Service
public class AwsS3Service implements StorageService {

    private final S3Client s3Client;
    private final StorageProperties storageProperties;
    private final AwsProperties awsProperties;

    public AwsS3Service(S3Client s3Client, AwsProperties awsProperties, StorageProperties storageProperties) {
        this.s3Client = s3Client;
        this.storageProperties = storageProperties;
        this.awsProperties = awsProperties;
    }

    public URL upload(@NotNull MultipartFile file,
                      @NotNull Path path) throws ThirdPartyStorageException {

        String basePath = storageProperties.uri().basePath();
        String key = Paths.get(basePath + path, file.getOriginalFilename()).toString();

        String bucketName = awsProperties.s3().bucketName();
        try {
            // S3에 파일 업로드 요청
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType(file.getContentType())
                    .build();

            RequestBody requestBody = RequestBody.fromBytes(file.getBytes());
            s3Client.putObject(request, requestBody);
        } catch (AwsServiceException e) {
            throw new ThirdPartyStorageException("S3 예외가 발생하여, 파일 업로드에 실패하였습니다.", e);
        } catch (IOException e) {
            throw new InvalidFileRequestException("유저가 업로드한 파일이 유효하지 못해 업로드 할 수 없습니다.", e);
        }
        return s3Client.utilities()
                .getUrl(builder -> builder.bucket(bucketName)
                        .key(key));
    }

    @Override
    public String getUrlString(String path) {
        return this.getUrlString(path, storageProperties);
    }

}
