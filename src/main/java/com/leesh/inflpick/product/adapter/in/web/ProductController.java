package com.leesh.inflpick.product.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorResponse;
import com.leesh.inflpick.common.port.in.FileTypeValidator;
import com.leesh.inflpick.product.adapter.in.web.value.ProductCreateApiErrorCode;
import com.leesh.inflpick.product.adapter.in.web.value.ProductReadApiErrorCode;
import com.leesh.inflpick.product.adapter.in.web.value.ProductRequest;
import com.leesh.inflpick.product.adapter.in.web.value.ProductResponse;
import com.leesh.inflpick.product.core.Product;
import com.leesh.inflpick.product.port.in.ProductCreateCommand;
import com.leesh.inflpick.product.port.in.ProductCreateService;
import com.leesh.inflpick.product.port.in.ProductReadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpHeaders.ACCEPT;

@Tag(name = "제품 API", description = "제품 API 명세서입니다.")
@RequiredArgsConstructor
@RequestMapping(path = "/api/products")
@RestController
public class ProductController {

    private final ProductCreateService createService;
    private final ProductReadService readService;

    @ApiErrorCodeSwaggerDocs(values = {ProductCreateApiErrorCode.class}, httpMethod = "POST", apiPath = "/api/products")
    @Operation(summary = "제품 생성", description = "제품을 생성합니다. 요청 예시에 있는 키워드 UUID 값은 실제 존재하는 값이 아니므로, 키워드 등록 후 실제 UUID 값으로 변경 후 요청해주세요.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공", headers = @Header(name = "Location", description = "생성된 리소스의 URI", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "400", description = "입력 값이 잘못된 경우", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Parameter(description = "제품 생성 요청 정보가 담긴 JSON 데이터 (schemas의 \"제품 생성 요청\" 항목 참고 바랍니다.)", required = true)
                       @RequestPart(value = "request")
                       ProductRequest request,
                                 @Parameter(description = "제품 이미지 파일", content = @Content(mediaType = MediaType.IMAGE_JPEG_VALUE, schema = @Schema(type = "string", format = "binary")))
                       @RequestPart(value = "productImage", required = true)
                       MultipartFile productImage) {

        FileTypeValidator.validateImageFile(productImage);
        ProductCreateCommand command = request.toCommand();
        String uuid = createService.create(command, productImage);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(uuid)
                .toUri();

        return ResponseEntity.created(location)
                .header(ACCEPT, MediaType.MULTIPART_FORM_DATA_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

    @ApiErrorCodeSwaggerDocs(values = {ProductReadApiErrorCode.class}, httpMethod = "GET", apiPath = "/api/products/{uuid}")
    @Operation(summary = "제품 단건 조회", description = "제품을 단건 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ProductResponse.class))),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> read(@Parameter(description = "제품 UUID", required = true)
                                                @RequestParam String uuid) {
        Product product = readService.getByUuid(uuid);
        return ResponseEntity.ok(ProductResponse.from(product));
    }
}
