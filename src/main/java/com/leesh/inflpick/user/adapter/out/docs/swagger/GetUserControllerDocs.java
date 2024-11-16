package com.leesh.inflpick.user.adapter.out.docs.swagger;

import com.leesh.inflpick.common.adapter.out.docs.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.common.application.dto.PageResponse;
import com.leesh.inflpick.user.application.dto.GetUserResponse;
import com.leesh.inflpick.user.application.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "유저 API", description = "유저 API")
public interface GetUserControllerDocs {

    @Operation(summary = "내 프로필 조회",
            description = "내 프로필 정보를 조회합니다.",
            security = {
                    @SecurityRequirement(name = "Bearer-Auth")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = GetUserResponse.class)))
            })
    ResponseEntity<GetUserResponse> me(@Parameter(hidden = true) UserDetails userDetails);

    @ApiErrorCodeSwaggerDocs(values = {UserNotFoundException.class}, httpMethod = "GET", apiPath = "/users")
    @Operation(summary = "유저 단건 조회",
            description = "유저를 조회합니다.",
            security = {
                    @SecurityRequirement(name = "Bearer-Auth")
            },
            parameters = {
                    @Parameter(name = "id", description = "유저 ID", example = "67264864c67b2d08a657e2a9", schema = @Schema(implementation = String.class))
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = GetUserResponse.class)))
            })
    ResponseEntity<GetUserResponse> get(String id);

    @Operation(summary = "유저 목록 페이지 조회",
            description = "유저 목록 페이지를 조회합니다.",
            security = {
                    @SecurityRequirement(name = "Bearer-Auth")
            },
            parameters = {
                    @Parameter(name = "page", description = "페이지 번호 (기본값: 0)", example = "0", schema = @Schema(implementation = Integer.class)),
                    @Parameter(name = "size", description = "한 페이지 크기 (기본값: 20)", example = "20", schema = @Schema(implementation = Integer.class)),
                    @Parameter(name = "sort", description = "정렬 기준 (기본값: createdDate,asc), 정렬 기준이 올바르지 않은 값을 입력한 경우, 기본 값으로 동작", example = "createdDate,asc",
                            examples = {
                                    @ExampleObject(name = "createdDate,asc", value = "createdDate,asc", description = "생성일 기준 오름차순 정렬"),
                                    @ExampleObject(name = "createdDate,desc", value = "createdDate,desc", description = "생성일 기준 내림차순 정렬"),
                                    @ExampleObject(name = "lastModifiedDate,asc", value = "lastModifiedDate,asc", description = "수정일 기준 오름차순 정렬"),
                                    @ExampleObject(name = "lastModifiedDate,desc", value = "lastModifiedDate,desc", description = "수정일 기준 내림차순 정렬"),
                                    @ExampleObject(name = "nickname,asc", value = "nickname,asc", description = "유저 닉네임 기준 오름차순 정렬"),
                                    @ExampleObject(name = "nickname,desc", value = "nickname,desc", description = "유저 닉네임 기준 내림차순 정렬")
                            })
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = PageResponse.class)))
            })
    ResponseEntity<PageResponse<GetUserResponse>> getPage(@RequestParam(name = "page", required = false, defaultValue = "0")
                                                             Integer page,
                                                          @RequestParam(name = "size", required = false, defaultValue = "20")
                                                             Integer size,
                                                          @RequestParam(name = "sort", required = false, defaultValue = "createdDate,asc")
                                                             String[] sort);
}
