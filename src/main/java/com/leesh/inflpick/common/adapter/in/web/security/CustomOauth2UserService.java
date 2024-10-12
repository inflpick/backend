package com.leesh.inflpick.common.adapter.in.web.security;

import com.leesh.inflpick.user.adapter.in.web.Oauth2Type;
import com.leesh.inflpick.user.core.domain.Oauth2UserInfo;
import com.leesh.inflpick.user.core.domain.Role;
import com.leesh.inflpick.user.core.domain.User;
import com.leesh.inflpick.user.port.in.UserCommand;
import com.leesh.inflpick.user.port.in.UserCommandService;
import com.leesh.inflpick.user.port.in.UserQueryService;
import com.leesh.inflpick.user.port.out.NotSupportedOauth2TypeException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
    private final List<Oauth2UserConverter> oauth2UserConverters;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        Oauth2Type oauth2Type = Oauth2Type.from(registrationId);

        OAuth2User convertOauth2User = oauth2UserConverters.stream()
                .filter(converter -> converter.isSupport(oauth2Type))
                .findAny()
                .map(converter -> {
                    OAuth2User oAuth2User = super.loadUser(userRequest);
                    String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
                    return converter.convert(oAuth2User, userNameAttributeName);
                })
                .orElseThrow(() -> new NotSupportedOauth2TypeException(registrationId));

        Oauth2UserInfo oauth2UserInfo = Oauth2UserInfo.of(
                convertOauth2User.getAttribute("id"),
                oauth2Type);

        Optional<User> optionalUser = userQueryService.getOauth2User(oauth2UserInfo);
        if (optionalUser.isEmpty()) {
            registerOauth2User(convertOauth2User, oauth2UserInfo);
        }

        return convertOauth2User;
    }

    private void registerOauth2User(OAuth2User convertOauth2User, Oauth2UserInfo oauth2UserInfo) {
        UserCommand userCommand = UserCommand.builder()
                .nickname(convertOauth2User.getAttribute("nickname"))
                .role(Role.USER)
                .oauth2UserInfo(oauth2UserInfo)
                .build();
        userCommandService.create(userCommand);
    }
}
