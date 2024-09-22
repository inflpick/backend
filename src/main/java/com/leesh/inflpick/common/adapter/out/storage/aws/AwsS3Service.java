package com.leesh.inflpick.common.adapter.out.storage.aws;

import com.leesh.inflpick.common.adapter.out.storage.InvalidFileRequestException;
import com.leesh.inflpick.common.adapter.out.storage.ThirdPartyStorageException;
import com.leesh.inflpick.influencer.port.out.StorageService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Profile("prod")
@RequiredArgsConstructor
@Service
public class AwsS3Service implements StorageService {

    private final S3Client s3Client;
    private final AwsProperties awsProperties;

    public String upload(@NotNull MultipartFile file,
                         @NotNull Path resourcePath) throws ThirdPartyStorageException {

        String key = Paths.get(resourcePath.toString(),
                file.getOriginalFilename()).toString();

        try {
            // S3에 파일 업로드 요청
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(awsProperties.s3().bucket())
                    .key(key)
                    .build();

            RequestBody requestBody = RequestBody.fromBytes(file.getBytes());
            s3Client.putObject(request, requestBody);
        } catch (S3Exception e) {
            throw new ThirdPartyStorageException("S3 예외가 발생하여, 파일 업로드에 실패하였습니다.", e);
        } catch (IOException e) {
            throw new InvalidFileRequestException("유저가 업로드한 파일이 유효하지 못해 업로드 할 수 없습니다.", e);
        }

        return key;
    }

}
