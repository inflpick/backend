package com.leesh.inflpick.user.adapter.out.persistence;

import com.leesh.inflpick.common.adapter.out.persistence.PageSortConverter;
import com.leesh.inflpick.common.port.PageDetails;
import com.leesh.inflpick.common.port.PageQuery;
import com.leesh.inflpick.user.adapter.out.persistence.mongo.UserDocument;
import com.leesh.inflpick.user.adapter.out.persistence.mongo.UserMongoRepository;
import com.leesh.inflpick.user.core.domain.Oauth2UserInfo;
import com.leesh.inflpick.user.core.domain.User;
import com.leesh.inflpick.user.port.out.UserNotFoundException;
import com.leesh.inflpick.user.port.out.UserRepository;
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
    public void deleteById(String id) {

    }

    @Override
    public Optional<User> getByOauth2UserInfo(Oauth2UserInfo oauth2UserInfo) {
        return userMongoRepository.getByOauth2UserIdAndOauth2Type(oauth2UserInfo.id(), oauth2UserInfo.oauth2Type().name())
                .map(UserDocument::toEntity);
    }

    @Override
    public PageDetails<Collection<User>> getPage(PageQuery pageQuery) {

        Sort sortCriteria = PageSortConverter.convertSortCriteria(pageQuery.sortPairs());
        PageRequest pageRequest = PageRequest.of(pageQuery.page(),
                pageQuery.size(),
                sortCriteria);

        Page<UserDocument> documentPage = userMongoRepository.findAll(pageRequest);
        List<User> contents = documentPage.getContent().stream()
                .map(UserDocument::toEntity)
                .toList();

        String[] sortProperties = PageSortConverter.convertSortProperties(documentPage.getSort());
        return PageDetails.of(
                documentPage.getNumber(),
                documentPage.getSize(),
                documentPage.getTotalPages(),
                documentPage.getTotalElements(),
                sortProperties,
                contents);
    }
}
