package com.leesh.inflpick.v2.influencer.adapter.out.docs.swagger;

import com.leesh.inflpick.v2.common.adapter.out.docs.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.v2.influencer.application.exception.InfluencerNotFoundException;
import com.leesh.inflpick.v2.influencer.application.exception.InvalidProfileImageException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "인플루언서 API", description = "인플루언서 API 명세서입니다.")
public interface UpdateProfileImageControllerDocs {

    @ApiErrorCodeSwaggerDocs(values = {InfluencerNotFoundException.class, InvalidProfileImageException.class}, httpMethod = "PATCH", apiPath = "/influencers/{id}/profile-image")
    @Operation(summary = "인플루언서 프로필 이미지 수정",
            description = "인플루언서의 프로필 이미지를 수정합니다.",
            security = {
                    @SecurityRequirement(name = "Bearer-Auth")
            },
            parameters = {
                    @Parameter(name = "id", schema = @Schema(type = "string"), required = true, example = "6728d0aad5e37a27ed7e2907", description = "인플루언서 ID"),
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "성공 (본문 없음)")
            }
    )
    ResponseEntity<Void> updateProfileImage(@PathVariable(value = "id")
                                            String id,
                                            @Parameter(description = "프로필 이미지 파일", required = true)
                                            @RequestPart(value = "profileImage")
                                            MultipartFile profileImage);
}
