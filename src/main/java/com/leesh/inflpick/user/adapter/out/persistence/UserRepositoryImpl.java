package com.leesh.inflpick.user.adapter.out.persistence;

import com.leesh.inflpick.common.adapter.out.persistence.PageSortConverter;
import com.leesh.inflpick.common.port.PageResponse;
import com.leesh.inflpick.common.port.SortCriterion;
import com.leesh.inflpick.user.adapter.out.persistence.mongo.UserDocument;
import com.leesh.inflpick.user.adapter.out.persistence.mongo.UserMongoRepository;
import com.leesh.inflpick.user.adapter.out.persistence.mongo.UserPageResponse;
import com.leesh.inflpick.user.core.domain.Oauth2UserInfo;
import com.leesh.inflpick.user.core.domain.User;
import com.leesh.inflpick.user.port.UserSortField;
import com.leesh.inflpick.user.port.out.UserNotFoundException;
import com.leesh.inflpick.user.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserMongoRepository userMongoRepository;

    @Override
    public String save(User user) {
        UserDocument userDocument = UserDocument.from(user);
        userMongoRepository.save(userDocument);
        return userDocument.getId();
    }

    @Override
    public long count() {
        return userMongoRepository.count();
    }

    @Override
    public User getById(String id) throws UserNotFoundException {

        UserDocument userDocument = userMongoRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return userDocument.toEntity();
    }

    @Override
    public Optional<User> findByOauth2UserInfo(Oauth2UserInfo oauth2UserInfo) {
        return userMongoRepository.findByOauth2UserIdAndOauth2Type(oauth2UserInfo.id(), oauth2UserInfo.oauth2Type().name())
                .map(UserDocument::toEntity);
    }

    @Override
    public PageResponse<User> getPage(com.leesh.inflpick.common.port.PageRequest request) {
        Collection<SortCriterion> sortCriteria = request.sortCriteria(() -> Arrays.stream(UserSortField.values())
                .map(UserSortField::getValue)
                .toList());
        Sort sort = PageSortConverter.convertSortCriteria(sortCriteria);
        PageRequest pageRequest = PageRequest.of(request.page(),
                request.size(),
                sort);

        Page<UserDocument> documentPage = userMongoRepository.findAll(pageRequest);
        return UserPageResponse.from(documentPage);
    }
}
