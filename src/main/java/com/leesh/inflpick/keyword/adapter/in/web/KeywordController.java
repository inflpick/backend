package com.leesh.inflpick.keyword.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorResponse;
import com.leesh.inflpick.keyword.adapter.in.web.value.KeywordRequest;
import com.leesh.inflpick.keyword.adapter.in.web.value.KeywordResponse;
import com.leesh.inflpick.keyword.port.in.KeywordCommandService;
import com.leesh.inflpick.keyword.port.in.KeywordReadService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "키워드 API", description = "키워드 API 명세서입니다.")
@Builder
@RequiredArgsConstructor
@RequestMapping(path = "/keywords")
@RestController
public class KeywordController {

    private final KeywordCommandService createService;
    private final KeywordReadService readService;

//    @ApiErrorCodeSwaggerDocs(values = KeywordCreateApiErrorCode.class, httpMethod = "POST", apiPath = "/api/keywords")
    @Operation(summary = "키워드 생성", description = "키워드를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공", headers = @Header(name = "Location", description = "생성된 리소스의 URI", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "400", description = "입력 값이 잘못된 경우", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody KeywordRequest request) {
        String id = createService.create(request.toCommand());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity
                .created(location)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Operation(summary = "키워드 명으로 검색", description = "입력한 키워드 명과 \"유사한\" 키워드를 검색합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KeywordResponse>> search(@RequestParam(value = "name")
                                                        @Parameter(description = "키워드 명", example = "100만 유튜버", required = true)
                                                        String name) {

        List<KeywordResponse> bodies = readService.search(name)
                .stream()
                .map(KeywordResponse::from)
                .toList();
        return ResponseEntity.ok(bodies);
    }

}
