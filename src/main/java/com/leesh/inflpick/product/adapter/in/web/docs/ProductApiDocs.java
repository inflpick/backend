package com.leesh.inflpick.product.adapter.in.web.docs;

import com.leesh.inflpick.v2.adapter.out.docs.swagger.common.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.common.adapter.in.web.value.WebPageResponse;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerGetListsApiErrorCode;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerProfileImageUpdateApiErrorCode;
import com.leesh.inflpick.product.adapter.in.web.value.ProductCreateApiErrorCode;
import com.leesh.inflpick.product.adapter.in.web.value.ProductReadApiErrorCode;
import com.leesh.inflpick.product.adapter.in.web.value.ProductRequest;
import com.leesh.inflpick.product.adapter.in.web.value.ProductWebResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "제품 API", description = "제품 API 명세서입니다.")
public interface ProductApiDocs {

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
    ResponseEntity<Void> create(@RequestBody ProductRequest request);

    @ApiErrorCodeSwaggerDocs(values = {ProductReadApiErrorCode.class}, httpMethod = "GET", apiPath = "/products/{id}")
    @Operation(summary = "제품 단건 조회", description = "제품을 단건 조회합니다.",
            parameters = {
                    @Parameter(name = "id", description = "제품 ID", required = true, example = "f103314b-778c-49fc-ae9c-7956794a3bdf", schema = @Schema(implementation = String.class))
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ProductWebResponse.class))),
            })
    ResponseEntity<ProductWebResponse> read(@PathVariable(value = "id") String id);

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
    ResponseEntity<WebPageResponse<ProductWebResponse>> list(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                                    @RequestParam(name = "size", required = false, defaultValue = "20") Integer size,
                                                                    @RequestParam(name = "sort", required = false, defaultValue = "createdDate,asc") String[] sort);

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
            })
    ResponseEntity<Void> update(@PathVariable(value = "id") String id,
                                @RequestBody ProductRequest request);

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
    ResponseEntity<Void> updateProductImage(@PathVariable(value = "id") String id,
                                            @Parameter(description = "제품 이미지", required = true)
                                            @RequestPart(value = "productImage") MultipartFile productImage);

    @Operation(summary = "제품 삭제", description = "제품을 삭제합니다.", security = {
            @SecurityRequirement(name = "Bearer-Auth"),
    },
            parameters = {
                    @Parameter(name = "id", description = "제품 ID", required = true, example = "f103314b-778c-49fc-ae9c-7956794a3bdf", schema = @Schema(implementation = String.class))
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "성공 (본문 없음)"),
            })
    ResponseEntity<Void> delete(@PathVariable(value = "id")
                                @Parameter(description = "제품 ID", required = true)
                                String id);
}
