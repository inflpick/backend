package com.leesh.inflpick.v2.adapter.in.web.user;

import com.leesh.inflpick.v2.adapter.in.web.common.dto.request.WebOffsetPageRequest;
import com.leesh.inflpick.v2.adapter.in.web.common.dto.response.WebOffsetPageResponse;
import com.leesh.inflpick.v2.adapter.in.web.user.docs.swagger.getUserPagesControllerDocs;
import com.leesh.inflpick.v2.adapter.in.web.user.dto.response.UserWebResponse;
import com.leesh.inflpick.v2.appilcation.port.out.common.dto.OffsetPageRequest;
import com.leesh.inflpick.v2.appilcation.port.out.common.dto.OffsetPageResponse;
import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.appilcation.port.in.user.GetUserPageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class GetUserPagesPagesController implements getUserPagesControllerDocs {

    private final GetUserPageUseCase getUserPageUseCase;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebOffsetPageResponse<UserWebResponse>> getUserPage(@RequestParam(name = "page", required = false, defaultValue = "0")
                                                                              Integer page,
                                                                              @RequestParam(name = "size", required = false, defaultValue = "20")
                                                                              Integer size,
                                                                              @RequestParam(name = "sort", required = false, defaultValue = "createdDate,asc")
                                                                              String[] sort) {

        OffsetPageRequest request = WebOffsetPageRequest.create(page, size, sort);
        OffsetPageResponse<User> pageResponse = getUserPageUseCase.getPage(request);
        UserWebResponse[] webContents = pageResponse.getContents().stream()
                .map(UserWebResponse::from)
                .toList()
                .toArray(UserWebResponse[]::new);
        WebOffsetPageResponse<UserWebResponse> response = WebOffsetPageResponse.create(webContents, pageResponse);
        return ResponseEntity.ok()
                .body(response);
    }
}
