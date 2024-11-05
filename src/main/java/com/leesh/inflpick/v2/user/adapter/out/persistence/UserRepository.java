package com.leesh.inflpick.v2.user.adapter.out.persistence;

import com.leesh.inflpick.v2.common.adapter.out.persistence.SpringDataPageRequestConverter;
import com.leesh.inflpick.v2.common.application.dto.OffsetPageRequest;
import com.leesh.inflpick.v2.common.application.dto.OffsetPageResponse;
import com.leesh.inflpick.v2.common.application.dto.Sortable;
import com.leesh.inflpick.v2.user.adapter.out.persistence.mongo.UserDocument;
import com.leesh.inflpick.v2.user.adapter.out.persistence.mongo.UserMongoRepository;
import com.leesh.inflpick.v2.user.application.port.out.CommandUserPort;
import com.leesh.inflpick.v2.user.application.port.out.QueryUserPort;
import com.leesh.inflpick.v2.user.domain.User;
import com.leesh.inflpick.v2.user.domain.UserSortable;
import com.leesh.inflpick.v2.user.domain.vo.AuthenticationCode;
import com.leesh.inflpick.v2.user.domain.vo.Oauth2Info;
import com.leesh.inflpick.v2.user.domain.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepository implements CommandUserPort, QueryUserPort {

    private final UserMongoRepository userMongoRepository;

    @Override
    public UserId save(User user) {
        UserDocument userDocument = UserDocument.from(user);
        UserDocument saved = userMongoRepository.save(userDocument);
        return UserId.create(saved.id());
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
        return userMongoRepository.findById(userId.id())
                .map(UserDocument::toEntity);
    }

    @Override
    public Optional<User> query(AuthenticationCode authenticationCode) {
        return userMongoRepository.findByAuthenticationCode(authenticationCode.code())
                .map(UserDocument::toEntity);
    }

    @Override
    public OffsetPageResponse<User> query(OffsetPageRequest request) {
        Sortable sortable = () -> Arrays.stream(UserSortable.values())
                .map(UserSortable::name)
                .toList();
        PageRequest pageRequest = SpringDataPageRequestConverter.convert(request, sortable);
        Page<UserDocument> documentPage = userMongoRepository.findAll(pageRequest);
        List<User> users = documentPage.getContent().stream()
                .map(UserDocument::toEntity)
                .toList();
        return OffsetPageResponse.create(users,
                documentPage.getNumber(),
                documentPage.getTotalPages(),
                documentPage.getSize(),
                documentPage.getTotalElements(),
                documentPage.getSort().toString());
    }
}
