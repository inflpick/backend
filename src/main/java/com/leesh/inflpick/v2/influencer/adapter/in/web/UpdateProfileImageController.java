package com.leesh.inflpick.v2.influencer.adapter.in.web;

import com.leesh.inflpick.v2.influencer.application.port.in.UpdateProfileImageUseCase;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import com.leesh.inflpick.v2.shared.adapter.in.web.FileTypeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/influencers")
@RestController
public class UpdateProfileImageController implements UpdateProfileImageControllerDocs {

    private final UpdateProfileImageUseCase updateProfileImageUseCase;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping(path = "/{id}/profile-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateProfileImage(@PathVariable(value = "id")
                                                   String id,
                                                   @RequestPart(value = "profileImage")
                                                   MultipartFile profileImage) {

        FileTypeValidator.validateImageFile(profileImage);
        InfluencerId influencerId = InfluencerId.create(id);
        updateProfileImageUseCase.updateProfileImage(influencerId, profileImage);
        return ResponseEntity.noContent()
                .header(HttpHeaders.ACCEPT, MediaType.MULTIPART_FORM_DATA_VALUE)
                .build();
    }
}
