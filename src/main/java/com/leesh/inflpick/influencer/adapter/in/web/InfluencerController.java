package com.leesh.inflpick.influencer.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.WebOffsetPageRequest;
import com.leesh.inflpick.common.adapter.in.web.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.common.adapter.in.web.value.WebPageResponse;
import com.leesh.inflpick.common.port.PageRequest;
import com.leesh.inflpick.common.port.PageResponse;
import com.leesh.inflpick.common.port.in.FileTypeValidator;
import com.leesh.inflpick.common.port.out.StorageService;
import com.leesh.inflpick.influencer.adapter.in.web.value.*;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.port.InfluencerCommand;
import com.leesh.inflpick.influencer.port.in.InfluencerCommandService;
import com.leesh.inflpick.influencer.port.in.InfluencerQueryService;
import com.leesh.inflpick.product.port.in.ProductQueryService;
import com.leesh.inflpick.review.port.in.ReviewQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Tag(name = "인플루언서 API", description = "인플루언서 API 명세서입니다.")
@Builder
@RequiredArgsConstructor
@RequestMapping(path = "/influencers")
@RestController
public class InfluencerController {

    private final InfluencerCommandService commandService;
    private final InfluencerQueryService influencerQueryService;
    private final ProductQueryService productQueryService;
    private final ReviewQueryService reviewQueryService;
    private final StorageService storageService;

    @ApiErrorCodeSwaggerDocs(values = {InfluencerCreateApiErrorCode.class}, httpMethod = "POST", apiPath = "/influencers")
    @Operation(summary = "인플루언서 생성",
            description = "(관리자 전용) 인플루언서를 생성합니다. 요청 예시에 있는 키워드 id 값은 실제 존재하는 값이 아니므로, 키워드 등록 후 실제 id 값으로 변경 후 요청해주세요.",
            security = {
                    @SecurityRequirement(name = "Bearer-Auth")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "인플루언서 생성 요청 정보", required = true, content = @Content(schema = @Schema(implementation = InfluencerRequest.class))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "성공", headers = @Header(name = "location", description = "생성된 인플루언서의 URI", schema = @Schema(type = "string")))
            }
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody InfluencerRequest request) {

        InfluencerCommand command = request.toCommand();
        String id = commandService.create(command);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

    @ApiErrorCodeSwaggerDocs(values = {InfluencerReadApiErrorCode.class}, httpMethod = "GET", apiPath = "/influencers/{id}")
    @Operation(summary = "인플루언서 단건 조회", description = "인플루언서를 단건 조회합니다.",
            parameters = {
                    @Parameter(name = "id", schema = @Schema(type = "string"), required = true, example = "f103314b-778c-49fc-ae9c-7956794a3bdf", description = "인플루언서 ID")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = InfluencerWebResponse.class))),
            }
    )
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InfluencerWebResponse> get(@PathVariable String id) {
        Influencer influencer = influencerQueryService.query(id);
        String profileImageUrl = storageService.getUrlString(influencer.getProfileImagePath());
        return ResponseEntity.ok(InfluencerWebResponse.from(
                influencer,
                profileImageUrl
        ));
    }

    @ApiErrorCodeSwaggerDocs(values = {InfluencerGetListsApiErrorCode.class}, httpMethod = "GET", apiPath = "/influencers?page={page}&size={size}&sort={sortType,sortDirection}")
    @Operation(summary = "인플루언서 목록 조회",
            description = "인플루언서 목록을 조회합니다.",
            parameters = {
                    @Parameter(name = "page", description = "페이지 번호 (기본값: 0)", example = "0", schema = @Schema(implementation = Integer.class)),
                    @Parameter(name = "size", description = "한 페이지 크기 (기본값: 20)", example = "20", schema = @Schema(implementation = Integer.class)),
                    @Parameter(name = "sort", description = "정렬 기준 (기본값: createdDate,asc, 다중 정렬 가능), 정렬 기준이 올바르지 않은 값을 입력한 경우, 기본 값으로 동작", example = "createdDate,asc",
                            examples = {
                                    @ExampleObject(name = "createdDate,asc", value = "createdDate,asc", description = "생성일 기준 오름차순 정렬"),
                                    @ExampleObject(name = "lastModifiedDate,desc", value = "lastModifiedDate,desc", description = "수정일 기준 내림차순 정렬"),
                                    @ExampleObject(name = "name,asc", value = "name,asc", description = "인플루언서 이름 기준 오름차순 정렬")
                            })
            }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebPageResponse<InfluencerWebResponse>> list(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                                       @RequestParam(name = "size", required = false, defaultValue = "20") Integer size,
                                                                       @RequestParam(name = "sort", required = false, defaultValue = "createdDate,asc") String[] sort) {

        PageRequest request = new WebOffsetPageRequest(page, size, sort);
        PageResponse<Influencer> pageResponse = influencerQueryService.query(request);

        InfluencerWebResponse[] webContents = pageResponse.contents().stream().map(influencer -> {
                    String profileImageUrl = storageService.getUrlString(influencer.getProfileImagePath());
                    return InfluencerWebResponse.from(influencer, profileImageUrl);
                }).toArray(InfluencerWebResponse[]::new);

        WebPageResponse<InfluencerWebResponse> response = WebPageResponse.of(webContents, pageResponse);
        return ResponseEntity.ok(response);
    }

    @ApiErrorCodeSwaggerDocs(values = {InfluencerCreateApiErrorCode.class, InfluencerReadApiErrorCode.class}, httpMethod = "PUT", apiPath = "/influencers/{id}")
    @Operation(summary = "인플루언서 수정",
            description = "인플루언서를 수정합니다. 요청 예시에 있는 키워드 ID 값은 실제 존재하는 값이 아니므로, 키워드 등록 후 실제 ID 값으로 변경 후 요청해주세요.",
            security = {
                @SecurityRequirement(name = "Bearer-Auth")
            },
            parameters = {
                @Parameter(name = "id", schema = @Schema(type = "string"), required = true, example = "f103314b-778c-49fc-ae9c-7956794a3bdf", description = "인플루언서 ID")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "인플루언서 수정 요청 정보", required = true),
            responses = {
                @ApiResponse(responseCode = "204", description = "성공 (본문 없음)"),
            }
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable(value = "id")
                                       String id,
                                       @RequestBody
                                       InfluencerRequest request) {
        InfluencerCommand command = request.toCommand();
        commandService.update(id, command);
        return ResponseEntity.noContent()
                .header(HttpHeaders.ACCEPT, MediaType.MULTIPART_FORM_DATA_VALUE)
                .build();
    }

    @Operation(summary = "인플루언서 삭제",
            description = "인플루언서를 삭제합니다.",
            security = {
                    @SecurityRequirement(name = "Bearer-Auth")
            },
            parameters = {
                    @Parameter(name = "id", schema = @Schema(type = "string"), required = true, example = "f103314b-778c-49fc-ae9c-7956794a3bdf", description = "인플루언서 ID")
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "성공 (본문 없음)"),
            }
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable(value = "id")
                                       String id) {

        commandService.delete(id);
        return ResponseEntity.noContent()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @ApiErrorCodeSwaggerDocs(values = {InfluencerProfileImageUpdateApiErrorCode.class}, httpMethod = "PATCH", apiPath = "/influencers/{id}/profile-image")
    @Operation(summary = "인플루언서 프로필 이미지 수정",
            description = "인플루언서의 프로필 이미지를 수정합니다.",
            security = {
                    @SecurityRequirement(name = "Bearer-Auth")
            },
            parameters = {
                    @Parameter(name = "id", schema = @Schema(type = "string"), required = true, example = "f103314b-778c-49fc-ae9c-7956794a3bdf", description = "인플루언서 ID"),
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "성공 (본문 없음)")
            }
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping(path = "/{id}/profile-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateProfileImage(@PathVariable(value = "id")
                                                   String id,
                                                   @Parameter(description = "프로필 이미지 파일", required = true)
                                                   @RequestPart(value = "profileImage")
                                                   MultipartFile profileImage) {

        FileTypeValidator.validateImageFile(profileImage);
        commandService.updateProfileImage(id, profileImage);
        return ResponseEntity.noContent()
                .header(HttpHeaders.ACCEPT, MediaType.MULTIPART_FORM_DATA_VALUE)
                .build();
    }
}
