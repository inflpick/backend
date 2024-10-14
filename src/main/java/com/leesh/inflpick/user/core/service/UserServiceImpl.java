package com.leesh.inflpick.user.core.service;

import com.leesh.inflpick.common.port.PageDetails;
import com.leesh.inflpick.common.port.PageQuery;
import com.leesh.inflpick.common.port.out.UuidHolder;
import com.leesh.inflpick.user.core.domain.Oauth2UserInfo;
import com.leesh.inflpick.user.core.domain.User;
import com.leesh.inflpick.user.port.UserSortType;
import com.leesh.inflpick.user.port.in.AlreadyConnectedOauth2User;
import com.leesh.inflpick.user.port.in.UserCommand;
import com.leesh.inflpick.user.port.in.UserCommandService;
import com.leesh.inflpick.user.port.in.UserQueryService;
import com.leesh.inflpick.user.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserCommandService, UserQueryService {

    private final UserRepository userRepository;
    private final UuidHolder uuidHolder;

    @Override
    public String create(UserCommand command) {
        User user = command.toEntity(uuidHolder);
        userRepository.getByOauth2UserInfo(command.oauth2UserInfo())
                .ifPresent(u -> {
                    throw new AlreadyConnectedOauth2User(u.getCreatedDate());
                });
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getOauth2User(Oauth2UserInfo oauth2UserInfo) {
        return userRepository.getByOauth2UserInfo(oauth2UserInfo);
    }

    @Override
    public User getById(String id) {
        return userRepository.getById(id);
    }

    @Override
    public PageDetails<Collection<User>> getPage(PageQuery<UserSortType> pageQuery) {
        return userRepository.getPage(pageQuery);
    }
}
