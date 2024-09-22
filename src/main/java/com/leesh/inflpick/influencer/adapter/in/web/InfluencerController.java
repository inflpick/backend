package com.leesh.inflpick.influencer.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorResponse;
import com.leesh.inflpick.common.adapter.in.web.value.CommonApiErrorCode;
import com.leesh.inflpick.common.port.in.FileTypeValidator;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerCreateApiErrorCode;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerReadApiErrorCode;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerRequest;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerResponse;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.port.in.InfluencerCreateCommand;
import com.leesh.inflpick.influencer.port.in.InfluencerCreateService;
import com.leesh.inflpick.influencer.port.in.InfluencerReadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Tag(name = "인플루언서 API", description = "인플루언서 API 명세서입니다.")
@Builder
@RequiredArgsConstructor
@RequestMapping(path = "/api/influencers")
@RestController
public class InfluencerController {

    private final InfluencerCreateService createService;
    private final InfluencerReadService readService;

    @ApiErrorCodeSwaggerDocs(values = {CommonApiErrorCode.class, InfluencerCreateApiErrorCode.class}, httpMethod = "POST", apiPath = "/api/influencers")
    @Operation(summary = "인플루언서 생성", description = "인플루언서를 생성합니다. 요청 예시에 있는 키워드 UUID 값은 실제 존재하는 값이 아니므로, 키워드 등록 후 실제 UUID 값으로 변경 후 요청해주세요.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공", headers = @Header(name = "Location", description = "생성된 리소스의 URI", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "400", description = "입력 값이 잘못된 경우", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Parameter(description = "인플루언서 생성 요청 정보가 담긴 JSON 데이터 (schemas의 \"인플루언서 생성 요청\" 항목 참고 바랍니다.)", required = true)
                                       @RequestPart(value = "request")
                                       InfluencerRequest request,
                                       @Parameter(description = "프로필 이미지 파일", content = @Content(mediaType = MediaType.IMAGE_JPEG_VALUE, schema = @Schema(type = "string", format = "binary")))
                                       @RequestPart(value = "profileImage", required = true)
                                       MultipartFile profileImage) {

        FileTypeValidator.validateImageFile(profileImage);
        InfluencerCreateCommand command = request.toCommand();
        String uuid = createService.create(command, profileImage);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(uuid)
                .toUri();
        return ResponseEntity.created(location)
                .header(HttpHeaders.ACCEPT, MediaType.MULTIPART_FORM_DATA_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

    @ApiErrorCodeSwaggerDocs(values = {InfluencerReadApiErrorCode.class}, httpMethod = "GET", apiPath = "/api/influencers/{uuid}")
    @Operation(summary = "인플루언서 단건 조회", description = "인플루언서를 단건 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = InfluencerResponse.class))),
    })
    @GetMapping(path = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InfluencerResponse> read(@PathVariable
                                                   @Parameter(description = "고유 식별자(uuid)", required = true)
                                                   String uuid) {
        Influencer influencer = readService.getByUuid(uuid);
        return ResponseEntity.ok(InfluencerResponse.from(influencer));
    }
}
