package com.leesh.inflpick.influencer.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.WebOffsetPageRequest;
import com.leesh.inflpick.common.adapter.in.web.value.WebPageResponse;
import com.leesh.inflpick.common.port.PageRequest;
import com.leesh.inflpick.common.port.PageResponse;
import com.leesh.inflpick.common.port.in.FileTypeValidator;
import com.leesh.inflpick.common.port.out.StorageService;
import com.leesh.inflpick.influencer.adapter.in.web.docs.InfluencerApiDocs;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerWebRequest;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerWebResponse;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.port.InfluencerCommand;
import com.leesh.inflpick.influencer.port.in.InfluencerCommandService;
import com.leesh.inflpick.influencer.port.in.InfluencerQueryService;
import com.leesh.inflpick.product.port.in.ProductQueryService;
import com.leesh.inflpick.review.port.in.ReviewQueryService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Builder
@RequiredArgsConstructor
@RequestMapping(path = "/influencers")
@RestController
public class InfluencerController implements InfluencerApiDocs {

    private final InfluencerCommandService commandService;
    private final InfluencerQueryService influencerQueryService;
    private final ProductQueryService productQueryService;
    private final ReviewQueryService reviewQueryService;
    private final StorageService storageService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody InfluencerWebRequest request) {

        InfluencerCommand command = request.toCommand();
        String id = commandService.create(command);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InfluencerWebResponse> get(@PathVariable String id) {
        Influencer influencer = influencerQueryService.query(id);
        String profileImageUrl = storageService.getUrlString(influencer.getProfileImagePath());
        return ResponseEntity.ok(InfluencerWebResponse.from(
                influencer,
                profileImageUrl
        ));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebPageResponse<InfluencerWebResponse>> list(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                                       @RequestParam(name = "size", required = false, defaultValue = "20") Integer size,
                                                                       @RequestParam(name = "sort", required = false, defaultValue = "createdDate,asc") String[] sort) {

        PageRequest request = new WebOffsetPageRequest(page, size, sort);
        PageResponse<Influencer> pageResponse = influencerQueryService.query(request);

        InfluencerWebResponse[] webContents = pageResponse.contents().stream().map(influencer -> {
                    String profileImageUrl = storageService.getUrlString(influencer.getProfileImagePath());
                    return InfluencerWebResponse.from(influencer, profileImageUrl);
                }).toArray(InfluencerWebResponse[]::new);

        WebPageResponse<InfluencerWebResponse> response = WebPageResponse.of(webContents, pageResponse);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable(value = "id") String id,
                                       @RequestBody InfluencerWebRequest request) {
        InfluencerCommand command = request.toCommand();
        commandService.update(id, command);
        return ResponseEntity.noContent()
                .header(HttpHeaders.ACCEPT, MediaType.MULTIPART_FORM_DATA_VALUE)
                .build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable(value = "id")
                                       String id) {

        commandService.delete(id);
        return ResponseEntity.noContent()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping(path = "/{id}/profile-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateProfileImage(@PathVariable(value = "id")
                                                   String id,
                                                   @RequestPart(value = "profileImage")
                                                   MultipartFile profileImage) {

        FileTypeValidator.validateImageFile(profileImage);
        commandService.updateProfileImage(id, profileImage);
        return ResponseEntity.noContent()
                .header(HttpHeaders.ACCEPT, MediaType.MULTIPART_FORM_DATA_VALUE)
                .build();
    }
}
