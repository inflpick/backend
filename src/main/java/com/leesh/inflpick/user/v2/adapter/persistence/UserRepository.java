package com.leesh.inflpick.user.v2.adapter.persistence;

import com.leesh.inflpick.common.port.SortDirection;
import com.leesh.inflpick.user.v2.core.dto.OffsetPageRequest;
import com.leesh.inflpick.user.v2.core.dto.OffsetPageResponse;
import com.leesh.inflpick.user.v2.core.dto.SortCriterion;
import com.leesh.inflpick.user.v2.core.entity.User;
import com.leesh.inflpick.user.v2.core.entity.vo.Oauth2Info;
import com.leesh.inflpick.user.v2.core.entity.vo.UserId;
import com.leesh.inflpick.user.v2.port.out.CommandUserPort;
import com.leesh.inflpick.user.v2.port.out.QueryUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
class UserRepository implements CommandUserPort, QueryUserPort {

    private final UserMongoRepository userMongoRepository;

    @Override
    public UserId save(User user) {
        UserDocument userDocument = UserDocument.from(user);
        UserDocument saved = userMongoRepository.save(userDocument);
        return UserId.create(saved.getId());
    }

    @Override
    public Optional<User> query(Oauth2Info oauth2Info) {
        String providerName = oauth2Info.getProvider().name();
        String oauth2Id = oauth2Info.getId();
        return userMongoRepository.findByOauth2IdAndOauth2Provider(oauth2Id, providerName)
                .map(UserDocument::toEntity);
    }

    @Override
    public User query(UserId userId) {
        return userMongoRepository.findById(userId.value())
                .map(UserDocument::toEntity)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public OffsetPageResponse<User> query(OffsetPageRequest request) {

        Sort sortOrder = Sort.unsorted();

        Collection<SortCriterion> sortCriteria = request.getSortCriteria(() -> List.of("createdDate", "lastModifiedDate"));
        for (SortCriterion sortCriterion : sortCriteria) {
            String sortProperty = sortCriterion.sortProperty();
            SortDirection sortDirection = sortCriterion.sortDirection();
            Sort.Direction direction = sortDirection.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC;
            sortOrder = sortOrder.and(Sort.by(direction, sortProperty));
        }

        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize(), sortOrder);
        Page<UserDocument> documentPage = userMongoRepository.findAll(pageRequest);
        Collection<User> users = documentPage.getContent().stream().map(UserDocument::toEntity).toList();

        return OffsetPageResponse.create(users,
                documentPage.getNumber(),
                documentPage.getTotalPages(),
                request.getSize(),
                documentPage.getTotalElements(),
                documentPage.getSort().toString());
    }
}
