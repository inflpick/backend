package com.leesh.inflpick.influencer.adapter.out.docs.swagger;

import com.leesh.inflpick.common.adapter.out.docs.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.influencer.application.dto.InfluencerRequest;
import com.leesh.inflpick.influencer.application.exception.InfluencerNotFoundException;
import com.leesh.inflpick.influencer.domain.exception.NotSupportedSnsPlatformException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "인플루언서 API", description = "인플루언서 API")
public interface UpdateInfluencerControllerDocs {

    @ApiErrorCodeSwaggerDocs(values = {InfluencerNotFoundException.class, NotSupportedSnsPlatformException.class}, httpMethod = "PUT", apiPath = "/influencer/{id}")
    @Operation(summary = "인플루언서 수정하기",
            description = "인플루언서를 수정합니다.",
            security = {
                @SecurityRequirement(name = "Bearer-Auth")
            },
            parameters = {
                    @Parameter(name = "id", schema = @Schema(type = "string"), required = true, example = "6726946b272157735138c837", description = "인플루언서 ID"),
            },
            requestBody = @RequestBody(
                    content = @Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = InfluencerRequest.class))
            ),
            responses = {
                @ApiResponse(responseCode = "204", description = "인플루언서 수정 성공")
            }
    )
    ResponseEntity<Void> update(String id, InfluencerRequest command);
}
