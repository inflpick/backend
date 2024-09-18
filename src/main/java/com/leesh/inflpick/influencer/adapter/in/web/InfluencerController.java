package com.leesh.inflpick.influencer.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.common.adapter.in.web.ApiErrorResponse;
import com.leesh.inflpick.common.adapter.in.web.CommonApiErrorCode;
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

    @ApiErrorCodeSwaggerDocs(values = {CommonApiErrorCode.class, InfluencerCreateApiErrorCode.class}, httpMethod = "POST", apiPath = "/api/influencers")
    @Operation(summary = "인플루언서 생성", description = "인플루언서를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공", headers = @Header(name = "Location", description = "생성된 리소스의 URI", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "400", description = "입력값이 유효하지 않음", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody InfluencerRequest request) {
        InfluencerCreateCommand command = request.toCommand();
        Influencer influencer = createService.create(command);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(influencer.getUuid())
                .toUri();
        return ResponseEntity.created(location)
                .build();
    }

    @ApiErrorCodeSwaggerDocs(values = InfluencerReadApiErrorCode.class, httpMethod = "GET", apiPath = "/api/influencers/{id}")
    @Operation(summary = "인플루언서 단건 조회", description = "인플루언서를 단건 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = InfluencerResponse.class))),
    })
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InfluencerResponse> read(@PathVariable
                                                      @Parameter(description = "ID", required = true)
                                                      String id) {
        Influencer influencer = readService.getByUuid(id);
        return ResponseEntity.ok(InfluencerResponse.from(influencer));
    }
}
