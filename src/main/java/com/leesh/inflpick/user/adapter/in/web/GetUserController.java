package com.leesh.inflpick.user.adapter.in.web;

import com.leesh.inflpick.common.application.dto.PageRequest;
import com.leesh.inflpick.common.application.dto.PageResponse;
import com.leesh.inflpick.user.adapter.out.docs.swagger.GetUserControllerDocs;
import com.leesh.inflpick.user.application.dto.GetUserResponse;
import com.leesh.inflpick.user.application.port.in.GetUserUseCase;
import com.leesh.inflpick.user.domain.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(path = "/users")
@RestController
public class GetUserController implements GetUserControllerDocs {

    private final GetUserUseCase getUserUseCase;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping(path = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetUserResponse> me(@AuthenticationPrincipal UserDetails userDetails) {
        String userId = userDetails.getUsername();
        UserId id = UserId.create(userId);
        GetUserResponse getUserResponse = getUserUseCase.get(id);
        return ResponseEntity.ok().body(getUserResponse);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetUserResponse> get(@PathVariable(value = "id") String id) {
        UserId userId = UserId.create(id);
        GetUserResponse getUserResponse = getUserUseCase.get(userId);
        return ResponseEntity.ok().body(getUserResponse);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<GetUserResponse>> getPage(@RequestParam(name = "page", required = false, defaultValue = "0")
                                                                       Integer page,
                                                                 @RequestParam(name = "size", required = false, defaultValue = "20")
                                                                       Integer size,
                                                                 @RequestParam(name = "sort", required = false, defaultValue = "createdDate,asc")
                                                                       String[] sort) {

        PageRequest request = PageRequest.create(page, size, sort);
        PageResponse<GetUserResponse> userPage = getUserUseCase.getPage(request);
        return ResponseEntity.ok().body(userPage);
    }

}
