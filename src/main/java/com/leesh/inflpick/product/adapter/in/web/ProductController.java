package com.leesh.inflpick.product.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.WebOffsetPageRequest;
import com.leesh.inflpick.common.adapter.in.web.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.common.adapter.in.web.value.WebPageResponse;
import com.leesh.inflpick.common.port.PageRequest;
import com.leesh.inflpick.common.port.PageResponse;
import com.leesh.inflpick.common.port.in.FileTypeValidator;
import com.leesh.inflpick.common.port.out.StorageService;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerGetListsApiErrorCode;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerProfileImageUpdateApiErrorCode;
import com.leesh.inflpick.product.adapter.in.web.value.ProductCreateApiErrorCode;
import com.leesh.inflpick.product.adapter.in.web.value.ProductReadApiErrorCode;
import com.leesh.inflpick.product.adapter.in.web.value.ProductRequest;
import com.leesh.inflpick.product.adapter.in.web.value.ProductWebResponse;
import com.leesh.inflpick.product.core.domain.Product;
import com.leesh.inflpick.product.port.ProductCommand;
import com.leesh.inflpick.product.port.in.ProductCommandService;
import com.leesh.inflpick.product.port.in.ProductQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Tag(name = "제품 API", description = "제품 API 명세서입니다.")
@RequiredArgsConstructor
@RequestMapping(path = "/products")
@RestController
public class ProductController {

    private final ProductCommandService commandService;
    private final ProductQueryService queryService;
    private final StorageService storageService;

    @ApiErrorCodeSwaggerDocs(values = {ProductCreateApiErrorCode.class}, httpMethod = "POST", apiPath = "/products")
    @Operation(summary = "제품 생성", description = "제품을 생성합니다. 요청 예시에 있는 키워드 ID 값은 실제 존재하는 값이 아니므로, 키워드 등록 후 실제 ID 값으로 변경 후 요청해주세요.",
            security = {
                @SecurityRequirement(name = "Bearer-Auth")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "제품 생성 요청 정보", required = true, content = @Content(schema = @Schema(implementation = ProductRequest.class))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "성공", headers = {
                            @Header(name = "Location", description = "생성된 제품 ID", schema = @Schema(implementation = String.class))
                    }),
            })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody ProductRequest request) {

        ProductCommand command = request.toCommand();
        String id = commandService.create(command);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

    @ApiErrorCodeSwaggerDocs(values = {ProductReadApiErrorCode.class}, httpMethod = "GET", apiPath = "/products/{id}")
    @Operation(summary = "제품 단건 조회", description = "제품을 단건 조회합니다.",
            parameters = {
                    @Parameter(name = "id", description = "제품 ID", required = true, example = "f103314b-778c-49fc-ae9c-7956794a3bdf", schema = @Schema(implementation = String.class))
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ProductWebResponse.class))),
            })
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductWebResponse> read(@PathVariable(value = "id") String id) {

        Product product = queryService.query(id);
        String productImagePath = product.getProductImagePath();
        String productImageUrl = storageService.getUrlString(productImagePath);
        return ResponseEntity.ok(ProductWebResponse.from(
                product,
                productImageUrl
        ));
    }

    @ApiErrorCodeSwaggerDocs(values = {InfluencerGetListsApiErrorCode.class}, httpMethod = "GET", apiPath = "/products?page={page}&size={size}&sort={sortType,sortDirection}")
    @Operation(summary = "제품 목록 조회", description = "제품 목록을 조회합니다.",
            parameters = {
                    @Parameter(name = "page", description = "페이지 번호 (기본값: 0)", example = "0", schema = @Schema(implementation = Integer.class)),
                    @Parameter(name = "size", description = "한 페이지 크기 (기본값: 20)", example = "20", schema = @Schema(implementation = Integer.class)),
                    @Parameter(name = "sort", description = "정렬 기준 (기본값: createdDate,asc, 다중 정렬 가능), 정렬 기준이 올바르지 않은 값을 입력한 경우, 기본 값으로 동작", example = "createdDate,asc",
                            examples = {
                                    @ExampleObject(name = "createdDate,asc", value = "createdDate,asc", description = "생성일 기준 오름차순 정렬"),
                                    @ExampleObject(name = "lastModifiedDate,desc", value = "lastModifiedDate,desc", description = "수정일 기준 내림차순 정렬"),
                                    @ExampleObject(name = "name,asc", value = "name,asc", description = "제품명 기준 오름차순 정렬")
                            })
            })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebPageResponse<ProductWebResponse>> list(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                                    @RequestParam(name = "size", required = false, defaultValue = "20") Integer size,
                                                                    @RequestParam(name = "sort", required = false, defaultValue = "createdDate,asc") String[] sort) {

        PageRequest request = new WebOffsetPageRequest(page, size, sort);
        PageResponse<Product> pageResponse = queryService.query(request);

        ProductWebResponse[] contents = pageResponse.contents().stream().map(product -> {
            String productImagePath = product.getProductImagePath();
            String productImageUrl = storageService.getUrlString(productImagePath);
            return ProductWebResponse.from(product, productImageUrl);
        }).toArray(ProductWebResponse[]::new);

        WebPageResponse<ProductWebResponse> response = WebPageResponse.of(contents, pageResponse);
        return ResponseEntity.ok(response);
    }

    @ApiErrorCodeSwaggerDocs(values = {ProductCreateApiErrorCode.class, ProductReadApiErrorCode.class}, httpMethod = "PATCH", apiPath = "/products/{id}")
    @Operation(summary = "제품 수정", description = "제품을 수정합니다.", security = {
            @SecurityRequirement(name = "Bearer-Auth")
            },
            parameters = {
                    @Parameter(name = "id", description = "제품 ID", required = true, example = "f103314b-778c-49fc-ae9c-7956794a3bdf", schema = @Schema(implementation = String.class))
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "제품 수정 요청 정보", required = true, content = @Content(schema = @Schema(implementation = ProductRequest.class))),
            responses = {
                    @ApiResponse(responseCode = "204", description = "성공 (본문 없음)"),
            }
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable(value = "id") String id,
                                       @RequestBody ProductRequest request) {
        ProductCommand command = request.toCommand();
        commandService.update(id, command);
        return ResponseEntity.noContent()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @ApiErrorCodeSwaggerDocs(values = {InfluencerProfileImageUpdateApiErrorCode.class}, httpMethod = "PATCH", apiPath = "/products/{id}/product-image")
    @Operation(summary = "제품 이미지 수정", description = "제품 이미지를 수정합니다.", security = {
            @SecurityRequirement(name = "Bearer-Auth")
            },
            parameters = {
                    @Parameter(name = "id", description = "제품 ID", required = true, example = "f103314b-778c-49fc-ae9c-7956794a3bdf", schema = @Schema(implementation = String.class))
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "성공 (본문 없음)"),
            })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping(path = "/{id}/product-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateProductImage(@PathVariable(value = "id") String id,
                                                   @Parameter(description = "제품 이미지", required = true)
                                                   @RequestPart(value = "productImage") MultipartFile productImage) {
        FileTypeValidator.validateImageFile(productImage);
        commandService.updateProductImage(id, productImage);
        return ResponseEntity.noContent()
                .header(HttpHeaders.ACCEPT, MediaType.MULTIPART_FORM_DATA_VALUE)
                .build();
    }

    @Operation(summary = "제품 삭제", description = "제품을 삭제합니다.", security = {
            @SecurityRequirement(name = "Bearer-Auth"),
            },
            parameters = {
                    @Parameter(name = "id", description = "제품 ID", required = true, example = "f103314b-778c-49fc-ae9c-7956794a3bdf", schema = @Schema(implementation = String.class))
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "성공 (본문 없음)"),
            })
    @PreAuthorize("hasAuthority('ADMIN')")
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
