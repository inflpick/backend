package com.leesh.inflpick.v2.common.adapter.out.docs.swagger;

import com.leesh.inflpick.v2.common.adapter.in.web.dto.ApiErrorResponse;
import com.leesh.inflpick.v2.common.adapter.in.web.exception.*;
import com.leesh.inflpick.v2.common.adapter.in.web.filter.security.ExpiredAuthenticationException;
import com.leesh.inflpick.v2.common.adapter.in.web.filter.security.InvalidAuthenticationException;
import com.leesh.inflpick.v2.common.application.exception.ThirdPartyStorageException;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "공통 API", description = "공통 API")
public interface CommonControllerDocs {

    @ApiErrorCodeSwaggerDocs(values = {
            ServiceUnavailableException.class,
            InvalidRequestBodyException.class,
            MissingRequestPartException.class,
            MissingRequiredFieldsException.class,
            NotAdminUserException.class,
            NotImageTypeException.class,
            NotSupportHttpMediaTypeException.class,
            NotSupportHttpMethodException.class,
            ServiceUnavailableException.class,
            TooManyRequestsException.class,
            ThirdPartyStorageException.class,
            ExpiredAuthenticationException.class,
            InvalidAuthenticationException.class
    }, httpMethod = "*", apiPath = "/api/**")
    @Operation(summary = "공통 API 에러 응답",
            description = "공통 API 에러 응답을 명시합니다.")
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    ResponseEntity<Void> common();

    @Hidden
    void favicon();
}
