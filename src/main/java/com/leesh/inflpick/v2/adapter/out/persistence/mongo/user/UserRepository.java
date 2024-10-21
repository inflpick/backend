package com.leesh.inflpick.v2.adapter.out.persistence.mongo.user;

import com.leesh.inflpick.common.port.SortDirection;
import com.leesh.inflpick.v2.application.dto.common.OffsetPageQuery;
import com.leesh.inflpick.v2.application.dto.common.OffsetPage;
import com.leesh.inflpick.v2.application.dto.common.SortCriterion;
import com.leesh.inflpick.v2.application.port.out.user.CommandUserPort;
import com.leesh.inflpick.v2.application.port.out.user.QueryUserPort;
import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.AuthenticationCode;
import com.leesh.inflpick.v2.domain.user.vo.Oauth2Info;
import com.leesh.inflpick.v2.domain.user.vo.UserId;
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
    public Optional<User> query(UserId userId) {
        return userMongoRepository.findById(userId.getValue())
                .map(UserDocument::toEntity);
    }

    @Override
    public Optional<User> query(AuthenticationCode authenticationCode) {
        return userMongoRepository.findByAuthenticationCode(authenticationCode.value())
                .map(UserDocument::toEntity);
    }

    @Override
    public OffsetPage<User> query(OffsetPageQuery request) {

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

        return OffsetPage.create(users,
                documentPage.getNumber(),
                documentPage.getTotalPages(),
                request.getSize(),
                documentPage.getTotalElements(),
                documentPage.getSort().toString());
    }
}
