package com.leesh.inflpick.v2.adapter.in.web.user;

import com.leesh.inflpick.v2.adapter.in.web.common.dto.WebOffsetPageQuery;
import com.leesh.inflpick.v2.adapter.in.web.common.dto.WebOffsetPageResponse;
import com.leesh.inflpick.v2.adapter.in.web.user.dto.response.UserWebResponse;
import com.leesh.inflpick.v2.adapter.out.docs.swagger.user.QueryUserControllerDocs;
import com.leesh.inflpick.v2.application.dto.common.OffsetPage;
import com.leesh.inflpick.v2.application.dto.common.OffsetPageQuery;
import com.leesh.inflpick.v2.application.port.in.user.QueryUserUseCase;
import com.leesh.inflpick.v2.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class QueryUserController implements QueryUserControllerDocs {

    private final QueryUserUseCase queryUserUseCase;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebOffsetPageResponse<UserWebResponse>> query(@RequestParam(name = "page", required = false, defaultValue = "0")
                                                                              Integer page,
                                                                        @RequestParam(name = "size", required = false, defaultValue = "20")
                                                                              Integer size,
                                                                        @RequestParam(name = "sort", required = false, defaultValue = "createdDate,asc")
                                                                              String[] sort) {

        OffsetPageQuery request = WebOffsetPageQuery.create(page, size, sort);
        OffsetPage<User> pageResponse = queryUserUseCase.query(request);
        UserWebResponse[] webContents = pageResponse.getContents().stream()
                .map(UserWebResponse::from)
                .toList()
                .toArray(UserWebResponse[]::new);
        WebOffsetPageResponse<UserWebResponse> response = WebOffsetPageResponse.create(webContents, pageResponse);
        return ResponseEntity.ok()
                .body(response);
    }
}
