package com.leesh.inflpick.user.core.service;

import com.leesh.inflpick.common.port.PageRequest;
import com.leesh.inflpick.common.port.PageResponse;
import com.leesh.inflpick.common.port.out.UuidHolder;
import com.leesh.inflpick.user.core.domain.AuthenticationCode;
import com.leesh.inflpick.user.core.domain.AuthenticationProcess;
import com.leesh.inflpick.user.core.domain.Oauth2UserInfo;
import com.leesh.inflpick.user.core.domain.User;
import com.leesh.inflpick.user.port.in.*;
import com.leesh.inflpick.user.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserCommandService, UserQueryService {

    private final UserRepository userRepository;
    private final UuidHolder uuidHolder;

    @Override
    public String create(UserCommand command) {
        User user = command.toEntity(uuidHolder);
        this.query(command.oauth2UserInfo())
                .ifPresent(u -> {
                    throw new AlreadyConnectedOauth2User(u.getCreatedDate());
                });
        return userRepository.save(user);
    }

    @Override
    public AuthenticationProcess authenticate(User user) {
        String uuid = uuidHolder.uuid();
        AuthenticationCode code = AuthenticationCode.create(uuid);
        AuthenticationProcess process = user.startAuthentication(code);
        userRepository.save(user);
        return process;
    }

    @Override
    public Optional<User> query(Oauth2UserInfo oauth2UserInfo) {
        return userRepository.findByOauth2UserInfo(oauth2UserInfo);
    }

    @Override
    public User query(String id) {
        return userRepository.getById(id);
    }

    @Override
    public User query(AuthenticationCode authenticationCode) throws InvalidAuthenticationCodeException {
        return userRepository.findByAuthenticationCode(authenticationCode)
                .orElseThrow(() -> new InvalidAuthenticationCodeException("Invalid authentication code"));
    }

    @Override
    public PageResponse<User> query(PageRequest request) {
        return userRepository.getPage(request);
    }
}
