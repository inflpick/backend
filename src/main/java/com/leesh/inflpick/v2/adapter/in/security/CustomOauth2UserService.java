package com.leesh.inflpick.v2.adapter.in.security;

import com.leesh.inflpick.v2.appilcation.port.in.user.CommandUserUseCase;
import com.leesh.inflpick.v2.appilcation.port.in.user.QueryUserUseCase;
import com.leesh.inflpick.v2.appilcation.service.user.exception.UserNotFoundException;
import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.appilcation.dto.user.UserCommand;
import com.leesh.inflpick.v2.domain.user.exception.NotSupportedOauth2TypeException;
import com.leesh.inflpick.v2.domain.user.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
class CustomOauth2UserService extends DefaultOAuth2UserService {

    private final CommandUserUseCase commandUserUseCase;
    private final QueryUserUseCase queryUserUseCase;
    private final List<Oauth2UserConverter> oauth2UserConverters;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration()
                .getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        Oauth2Provider oauth2Provider = Oauth2Provider.from(registrationId);
        OAuth2User convertOauth2User = oauth2UserConverters.stream()
                .filter(converter -> converter.isSupport(oauth2Provider))
                .findAny()
                .orElseThrow(() -> new NotSupportedOauth2TypeException(oauth2Provider.name()))
                .convert(oAuth2User, userNameAttributeName);

        Oauth2Info oauth2Info = Oauth2Info.create(
                convertOauth2User.getName(),
                oauth2Provider);

        User user = findUserOrRegisterNewUser(oauth2Info, convertOauth2User);
        return new CustomOauth2User(user);
    }

    private User findUserOrRegisterNewUser(Oauth2Info oauth2Info, OAuth2User convertOauth2User) {
        User user;
        try {
            user = queryUserUseCase.query(oauth2Info);
        } catch (UserNotFoundException e) {
            UserId userId = registerNewUser(convertOauth2User, oauth2Info);
            user = queryUserUseCase.query(userId);
        }
        return user;
    }

    private UserId registerNewUser(OAuth2User convertOauth2User, Oauth2Info oauth2Info) {
        String nickname = convertOauth2User.getAttribute("nickname");
        String profileImageUrl = convertOauth2User.getAttribute("profileImageUrl");
        String email = convertOauth2User.getAttribute("email");
        Nickname userNickname = Nickname.create(nickname);
        UserEmail userEmail = UserEmail.create(email);
        UserCommand userCommand = UserCommand.builder(userNickname, oauth2Info)
                .email(userEmail)
                .profileImageUrl(profileImageUrl)
                .build();
        return commandUserUseCase.create(userCommand);
    }
}
