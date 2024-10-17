package com.leesh.inflpick.product.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.WebOffsetPageRequest;
import com.leesh.inflpick.common.adapter.in.web.value.WebPageResponse;
import com.leesh.inflpick.common.port.PageRequest;
import com.leesh.inflpick.common.port.PageResponse;
import com.leesh.inflpick.common.port.in.FileTypeValidator;
import com.leesh.inflpick.common.port.out.StorageService;
import com.leesh.inflpick.product.adapter.in.web.docs.ProductApiDocs;
import com.leesh.inflpick.product.adapter.in.web.value.ProductRequest;
import com.leesh.inflpick.product.adapter.in.web.value.ProductWebResponse;
import com.leesh.inflpick.product.core.domain.Product;
import com.leesh.inflpick.product.port.ProductCommand;
import com.leesh.inflpick.product.port.in.ProductCommandService;
import com.leesh.inflpick.product.port.in.ProductQueryService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping(path = "/products")
@RestController
public class ProductController implements ProductApiDocs {

    private final ProductCommandService commandService;
    private final ProductQueryService queryService;
    private final StorageService storageService;

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
