package com.leesh.inflpick.user.adapter.out.persistence;

import com.leesh.inflpick.user.adapter.out.persistence.mongo.UserDocument;
import com.leesh.inflpick.user.adapter.out.persistence.mongo.UserMongoRepository;
import com.leesh.inflpick.user.core.domain.Oauth2UserInfo;
import com.leesh.inflpick.user.core.domain.User;
import com.leesh.inflpick.user.port.out.UserNotFoundException;
import com.leesh.inflpick.user.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
    public User getById(String uuid) throws UserNotFoundException {

        UserDocument userDocument = userMongoRepository.findById(uuid)
                .orElseThrow(() -> new UserNotFoundException(uuid));

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
}
