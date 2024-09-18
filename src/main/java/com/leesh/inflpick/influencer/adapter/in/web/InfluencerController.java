package com.leesh.inflpick.influencer.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.common.adapter.in.web.ApiErrorResponse;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @ApiErrorCodeSwaggerDocs(values = {InfluencerCreateApiErrorCode.class}, httpMethod = "POST", apiPath = "/api/influencers")
    @Operation(summary = "인플루언서 생성", description = "인플루언서를 생성합니다. 요청 예시에 있는 키워드 UUID 값은 실제 존재하는 값이 아니므로, 키워드 등록 후 실제 UUID 값으로 변경 후 요청해주세요.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공", headers = @Header(name = "Location", description = "생성된 리소스의 URI", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "400", description = "입력 값이 잘못된 경우", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody InfluencerRequest request) {
        InfluencerCreateCommand command = request.toCommand();
        Influencer influencer = createService.create(command);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(influencer.getUuid())
                .toUri();
        return ResponseEntity.created(location)
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
