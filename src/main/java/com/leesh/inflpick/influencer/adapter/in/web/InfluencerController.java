package com.leesh.inflpick.influencer.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorResponse;
import com.leesh.inflpick.common.adapter.in.web.value.PageRequest;
import com.leesh.inflpick.common.adapter.in.web.value.PageResponse;
import com.leesh.inflpick.common.port.PageDetails;
import com.leesh.inflpick.common.port.in.FileTypeValidator;
import com.leesh.inflpick.influencer.adapter.in.web.value.*;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.port.InfluencerCommand;
import com.leesh.inflpick.influencer.port.InfluencerPageQuery;
import com.leesh.inflpick.influencer.port.in.InfluencerCommandService;
import com.leesh.inflpick.influencer.port.in.InfluencerQueryService;
import com.leesh.inflpick.product.adapter.in.web.value.ProductReadApiErrorCode;
import com.leesh.inflpick.product.core.domain.Product;
import com.leesh.inflpick.product.port.in.ProductQueryService;
import com.leesh.inflpick.review.core.domain.Review;
import com.leesh.inflpick.review.port.in.ReviewCommand;
import com.leesh.inflpick.review.port.in.ReviewCommandService;
import com.leesh.inflpick.review.port.in.ReviewQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "인플루언서 API", description = "인플루언서 API 명세서입니다.")
@Builder
@RequiredArgsConstructor
@RequestMapping(path = "/api/influencers")
@RestController
public class InfluencerController {

    private final InfluencerCommandService commandService;
    private final InfluencerQueryService influencerQueryService;
    private final ReviewCommandService reviewCommandService;
    private final ProductQueryService productQueryService;
    private final ReviewQueryService reviewQueryService;

    @ApiErrorCodeSwaggerDocs(values = {InfluencerCreateApiErrorCode.class}, httpMethod = "POST", apiPath = "/api/influencers")
    @Operation(summary = "인플루언서 생성", description = "인플루언서를 생성합니다. 요청 예시에 있는 키워드 id 값은 실제 존재하는 값이 아니므로, 키워드 등록 후 실제 id 값으로 변경 후 요청해주세요.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공", headers = @Header(name = "Location", description = "생성된 인플루언서의 URI", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "400", description = "입력 값이 잘못된 경우", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "인플루언서 생성 요청 정보", required = true)
                                       @RequestBody
                                       InfluencerRequest request) {

        InfluencerCommand command = request.toCommand();
        String id = commandService.create(command);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location)
                .header(HttpHeaders.ACCEPT, MediaType.MULTIPART_FORM_DATA_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

    @ApiErrorCodeSwaggerDocs(values = {InfluencerReadApiErrorCode.class}, httpMethod = "GET", apiPath = "/api/influencers/{id}")
    @Operation(summary = "인플루언서 단건 조회", description = "인플루언서를 단건 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = InfluencerResponse.class))),
    })
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InfluencerResponse> get(@PathVariable
                                                  @Parameter(description = "인플루언서 ID", required = true)
                                                  String id) {
        Influencer influencer = influencerQueryService.getById(id);
        return ResponseEntity.ok(InfluencerResponse.from(influencer));
    }

    @ApiErrorCodeSwaggerDocs(values = {InfluencerGetListsApiErrorCode.class}, httpMethod = "GET", apiPath = "/api/influencers?page={page}&size={size}&sort={sortType,sortDirection}")
    @Operation(summary = "인플루언서 목록 조회", description = "인플루언서 목록을 조회합니다.")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<InfluencerResponse>> list(@Parameter(description = "페이지 번호 (기본값: 0)", example = "0", schema = @Schema(implementation = Integer.class))
                                                                       @RequestParam(name = "page", required = false, defaultValue = "0")
                                                                       Integer page,
                                                                       @Parameter(description = "한 페이지 크기 (기본값: 20)", example = "20", schema = @Schema(implementation = Integer.class))
                                                                       @RequestParam(name = "size", required = false, defaultValue = "20")
                                                                       Integer size,
                                                                       @Parameter(description = "정렬 기준 (여러개 가능) [name | createdDate | lastModifiedDate] 중 하나 (기본값: createdDate,asc)", example = "createdDate,asc", array = @ArraySchema(schema = @Schema(implementation = String.class)))
                                                                       @RequestParam(name = "sort", required = false, defaultValue = "createdDate,asc")
                                                                       String[] sort) {

        PageRequest request = new PageRequest(page, size, sort);
        InfluencerPageQuery query = InfluencerPageQuery.from(request);
        PageDetails<List<Influencer>> influencerPage = influencerQueryService.getPage(query);
        List<InfluencerResponse> influencerResponses = convertToResponse(influencerPage);
        PageResponse<InfluencerResponse> pageResponse = new PageResponse<>(
                influencerResponses.toArray(InfluencerResponse[]::new),
                influencerPage.currentPage(),
                influencerPage.totalPages(),
                influencerPage.size(),
                influencerPage.sorts(),
                influencerPage.totalElements());
        return ResponseEntity.ok(pageResponse);
    }

    private static @NotNull List<InfluencerResponse> convertToResponse(PageDetails<List<Influencer>> influencerPage) {
        return influencerPage.content()
                .stream()
                .map(InfluencerResponse::from)
                .toList();
    }

    @ApiErrorCodeSwaggerDocs(values = {InfluencerCreateApiErrorCode.class, InfluencerReadApiErrorCode.class}, httpMethod = "PUT", apiPath = "/api/influencers/{id}")
    @Operation(summary = "인플루언서 수정", description = "인플루언서를 수정합니다. 요청 예시에 있는 키워드 ID 값은 실제 존재하는 값이 아니므로, 키워드 등록 후 실제 ID 값으로 변경 후 요청해주세요.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "성공 (본문 없음)"),
            @ApiResponse(responseCode = "400", description = "입력 값이 잘못된 경우", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@Parameter(description = "인플루언서 ID", required = true)
                                       @PathVariable(value = "id")
                                       String id,
                                       @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "인플루언서 수정 요청 정보", required = true)
                                       @RequestBody
                                       InfluencerRequest request) {
        InfluencerCommand command = request.toCommand();
        commandService.update(id, command);
        return ResponseEntity.noContent()
                .header(HttpHeaders.ACCEPT, MediaType.MULTIPART_FORM_DATA_VALUE)
                .build();
    }

    @Operation(summary = "인플루언서 삭제", description = "인플루언서를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "성공 (본문 없음)"),
    })
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@Parameter(description = "인플루언서 ID", required = true)
                                       @PathVariable(value = "id")
                                       String id) {
        commandService.delete(id);
        return ResponseEntity.noContent()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @ApiErrorCodeSwaggerDocs(values = {InfluencerProfileImageUpdateApiErrorCode.class}, httpMethod = "PATCH", apiPath = "/api/influencers/{id}/profile-image")
    @Operation(summary = "인플루언서 프로필 이미지 수정", description = "인플루언서의 프로필 이미지를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "성공 (본문 없음)"),
            @ApiResponse(responseCode = "400", description = "입력 값이 잘못된 경우", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @PatchMapping(path = "/{id}/profile-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateProfileImage(@Parameter(description = "인플루언서 ID", required = true)
                                                   @PathVariable(value = "id")
                                                   String id,
                                                   @Parameter(description = "프로필 이미지 파일", content = @Content(mediaType = MediaType.IMAGE_JPEG_VALUE, schema = @Schema(type = "string", format = "binary")))
                                                   @RequestPart(value = "profileImage", required = true)
                                                   MultipartFile profileImage) {
        FileTypeValidator.validateImageFile(profileImage);
        commandService.updateProfileImage(id, profileImage);
        return ResponseEntity.noContent()
                .header(HttpHeaders.ACCEPT, MediaType.MULTIPART_FORM_DATA_VALUE)
                .build();
    }

    @ApiErrorCodeSwaggerDocs(values = {InfluencerReviewsApiErrorCode.class, ProductReadApiErrorCode.class, InfluencerReadApiErrorCode.class}, httpMethod = "POST", apiPath = "/api/influencers/{id}/reviews")
    @Operation(summary = "인플루언서 제품 리뷰하기", description = "인플루언서의 제품 리뷰를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공", headers = @Header(name = "Location", description = "생성된 리뷰의 URI", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "400", description = "입력 값이 잘못된 경우", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @PostMapping(path = "/{id}/reviews", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> review(@Parameter(description = "인플루언서 ID", required = true)
                                       @PathVariable(value = "id")
                                       String id,
                                       @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "인플루언서 리뷰 생성 요청 정보", required = true)
                                       @RequestBody
                                       InfluencerReviewRequest request) {
        Influencer reviewer = influencerQueryService.getById(id);
        Product product = productQueryService.getById(request.productId());
        ReviewCommand command = request.toCommand();
        String reviewId = reviewCommandService.create(reviewer, product, command);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{reviewId}")
                .buildAndExpand(reviewId)
                .toUri();
        return ResponseEntity.created(location)
                .header(HttpHeaders.ACCEPT, MediaType.MULTIPART_FORM_DATA_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

    @ApiErrorCodeSwaggerDocs(values = {ProductReadApiErrorCode.class, InfluencerReadApiErrorCode.class}, httpMethod = "GET", apiPath = "/api/influencers/{id}/reviews")
    @Operation(summary = "인플루언서가 리뷰한 제품들을 조회", description = "인플루언서가 리뷰한 제품들을 조회합니다.")
    @GetMapping(path = "/{id}/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InfluencerReviewResponse> getProductReviews(@Parameter(description = "인플루언서 ID", required = true)
                                                                      @PathVariable(value = "id")
                                                                      String id) {
        Influencer influencer = influencerQueryService.getById(id);
        List<Review> reviews = reviewQueryService.getAllByReviewerId(id);
        InfluencerReviewResponse response = InfluencerReviewResponse.from(influencer, reviews);
        return ResponseEntity.ok(response);
    }
}
