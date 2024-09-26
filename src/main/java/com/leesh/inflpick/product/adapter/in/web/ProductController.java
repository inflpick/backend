package com.leesh.inflpick.product.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorResponse;
import com.leesh.inflpick.common.adapter.in.web.value.PageRequest;
import com.leesh.inflpick.common.adapter.in.web.value.PageResponse;
import com.leesh.inflpick.common.port.PageDetails;
import com.leesh.inflpick.common.port.in.FileTypeValidator;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerGetListsApiErrorCode;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerProfileImageUpdateApiErrorCode;
import com.leesh.inflpick.product.adapter.in.web.value.ProductCreateApiErrorCode;
import com.leesh.inflpick.product.adapter.in.web.value.ProductReadApiErrorCode;
import com.leesh.inflpick.product.adapter.in.web.value.ProductRequest;
import com.leesh.inflpick.product.adapter.in.web.value.ProductResponse;
import com.leesh.inflpick.product.core.domain.Product;
import com.leesh.inflpick.product.port.ProductCommand;
import com.leesh.inflpick.product.port.ProductPageQuery;
import com.leesh.inflpick.product.port.in.ProductCommandService;
import com.leesh.inflpick.product.port.in.ProductQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import static org.springframework.http.HttpHeaders.ACCEPT;

@Tag(name = "제품 API", description = "제품 API 명세서입니다.")
@RequiredArgsConstructor
@RequestMapping(path = "/api/products")
@RestController
public class ProductController {

    private final ProductCommandService commandService;
    private final ProductQueryService queryService;

//    @ApiErrorCodeSwaggerDocs(values = {ProductCreateApiErrorCode.class}, httpMethod = "POST", apiPath = "/api/products")
    @Operation(summary = "제품 생성", description = "제품을 생성합니다. 요청 예시에 있는 키워드 ID 값은 실제 존재하는 값이 아니므로, 키워드 등록 후 실제 ID 값으로 변경 후 요청해주세요.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공", headers = @Header(name = "Location", description = "생성된 리소스의 URI", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "400", description = "입력 값이 잘못된 경우", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(
                        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "제품 생성 요청 정보", required = true)
                        @RequestBody
                       ProductRequest request) {

        ProductCommand command = request.toCommand();
        String id = commandService.create(command);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location)
                .header(ACCEPT, MediaType.MULTIPART_FORM_DATA_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

//    @ApiErrorCodeSwaggerDocs(values = {ProductReadApiErrorCode.class}, httpMethod = "GET", apiPath = "/api/products/{id}")
    @Operation(summary = "제품 단건 조회", description = "제품을 단건 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ProductResponse.class))),
    })
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> read(@PathVariable(value = "id")
                                                @Parameter(description = "제품 ID", required = true)
                                                String id) {
        Product product = queryService.getById(id);
        return ResponseEntity.ok(ProductResponse.from(product));
    }

//    @ApiErrorCodeSwaggerDocs(values = {InfluencerGetListsApiErrorCode.class}, httpMethod = "GET", apiPath = "/api/products?page={page}&size={size}&sort={sortType,sortDirection}")
    @Operation(summary = "제품 목록 조회", description = "제품 목록을 조회합니다.")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<ProductResponse>> list(@Parameter(description = "페이지 번호 (기본값: 0)", example = "0", schema = @Schema(implementation = Integer.class))
                                                                       @RequestParam(name = "page", required = false, defaultValue = "0")
                                                                       Integer page,
                                                                       @Parameter(description = "한 페이지 크기 (기본값: 20)", example = "20", schema = @Schema(implementation = Integer.class))
                                                                       @RequestParam(name = "size", required = false, defaultValue = "20")
                                                                       Integer size,
                                                                       @Parameter(description = "정렬 기준 (여러개 가능) [name | createdDate | lastModifiedDate] 중 하나 (기본값: createdDate,asc)", example = "createdDate,asc", array = @ArraySchema(schema = @Schema(implementation = String.class)))
                                                                       @RequestParam(name = "sort", required = false, defaultValue = "createdDate,asc")
                                                                       String[] sort) {

        PageRequest request = new PageRequest(page, size, sort);
        ProductPageQuery query = ProductPageQuery.from(request);
        PageDetails<List<Product>> productPage = queryService.getPage(query);
        List<ProductResponse> productResponses = convertToResponse(productPage);
        PageResponse<ProductResponse> pageResponse = new PageResponse<>(
                productResponses.toArray(ProductResponse[]::new),
                productPage.currentPage(),
                productPage.totalPages(),
                productPage.size(),
                productPage.sorts(),
                productPage.totalElements());
        return ResponseEntity.ok(pageResponse);
    }

//    @ApiErrorCodeSwaggerDocs(values = {ProductCreateApiErrorCode.class, ProductReadApiErrorCode.class}, httpMethod = "PATCH", apiPath = "/api/products/{id}")
    @Operation(summary = "제품 수정", description = "제품을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "성공 (본문 없음)"),
            @ApiResponse(responseCode = "400", description = "입력 값이 잘못된 경우", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@Parameter(description = "제품 ID", required = true)
                                       @PathVariable(value = "id")
                                       String id,
                                       @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "제품 수정 요청 정보", required = true)
                                       @RequestBody
                                       ProductRequest request) {
        ProductCommand command = request.toCommand();
        commandService.update(id, command);
        return ResponseEntity.noContent()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

//    @ApiErrorCodeSwaggerDocs(values = {InfluencerProfileImageUpdateApiErrorCode.class}, httpMethod = "PATCH", apiPath = "/api/products/{id}/product-image")
    @Operation(summary = "제품 이미지 수정", description = "제품 이미지를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "성공 (본문 없음)"),
            @ApiResponse(responseCode = "400", description = "입력 값이 잘못된 경우", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @PatchMapping(path = "/{id}/product-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateProductImage(@Parameter(description = "제품 ID", required = true)
                                                   @PathVariable(value = "id")
                                                   String id,
                                                   @Parameter(description = "제품 이미지 파일", content = @Content(mediaType = MediaType.IMAGE_JPEG_VALUE, schema = @Schema(type = "string", format = "binary")))
                                                   @RequestPart(value = "productImage", required = true)
                                                   MultipartFile productImage) {
        FileTypeValidator.validateImageFile(productImage);
        commandService.updateProductImage(id, productImage);
        return ResponseEntity.noContent()
                .header(HttpHeaders.ACCEPT, MediaType.MULTIPART_FORM_DATA_VALUE)
                .build();
    }

    private static @NotNull List<ProductResponse> convertToResponse(PageDetails<List<Product>> productPage) {
        return productPage.content()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

    @Operation(summary = "제품 삭제", description = "제품을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "성공 (본문 없음)")
    })
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable(value = "id")
                                       @Parameter(description = "제품 ID", required = true)
                                       String id) {
        commandService.delete(id);
        return ResponseEntity.noContent()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
