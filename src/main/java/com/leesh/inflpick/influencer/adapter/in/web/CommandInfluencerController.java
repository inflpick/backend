package com.leesh.inflpick.influencer.adapter.in.web;

import com.leesh.inflpick.v2.shared.adapter.in.web.FileTypeValidator;
import com.leesh.inflpick.v2.influencer.adapter.in.web.docs.CommandInfluencerControllerDocs;
import com.leesh.inflpick.influencer.port.in.InfluencerCommandService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@RequiredArgsConstructor
@RequestMapping(path = "/influencers")
@RestController
public class CommandInfluencerController implements CommandInfluencerControllerDocs {

    private final InfluencerCommandService commandService;


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
