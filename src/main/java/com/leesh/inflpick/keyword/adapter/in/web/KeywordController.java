package com.leesh.inflpick.keyword.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.keyword.adapter.in.web.value.KeywordCreateApiErrorCode;
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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    private final KeywordCommandService commandService;
    private final KeywordReadService readService;

    @ApiErrorCodeSwaggerDocs(values = KeywordCreateApiErrorCode.class, httpMethod = "POST", apiPath = "/keywords")
    @Operation(summary = "키워드 생성", description = "키워드를 생성합니다.",
            security = {@SecurityRequirement(name = "Bearer-Auth")},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = KeywordRequest.class))))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공", headers = @Header(name = "Location", description = "생성된 리소스의 URI", schema = @Schema(type = "string"))),
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody KeywordRequest request) {
        String id = commandService.create(request.toCommand());
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

    @Operation(summary = "키워드 명으로 검색", description = "입력한 키워드 명과 \"유사한\" 키워드를 검색합니다.",
    parameters = {
            @Parameter(name = "name", description = "키워드 명", required = true, example = "100만 유튜버")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KeywordResponse>> search(@RequestParam(value = "name") String name) {

        List<KeywordResponse> bodies = readService.search(name)
                .stream()
                .map(KeywordResponse::from)
                .toList();
        return ResponseEntity.ok(bodies);
    }

    @ApiErrorCodeSwaggerDocs(values = KeywordCreateApiErrorCode.class, httpMethod = "PUT", apiPath = "/keywords")
    @Operation(summary = "키워드 수정", description = "키워드 수정",
            security = {@SecurityRequirement(name = "Bearer-Auth")},
            parameters = {@Parameter(name = "id", description = "키워드 ID", required = true, example = "92624c72-1cf2-4762-8c45-fe1a1f0a3e97")},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = KeywordRequest.class))))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "성공")
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable String id,
                                       @RequestBody KeywordRequest request) {
        commandService.update(id, request.toCommand());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "키워드 삭제", description = "키워드 삭제",
            security = {@SecurityRequirement(name = "Bearer-Auth")},
            parameters = {@Parameter(name = "id", description = "키워드 ID", required = true, example = "92624c72-1cf2-4762-8c45-fe1a1f0a3e97")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "성공")
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        commandService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
