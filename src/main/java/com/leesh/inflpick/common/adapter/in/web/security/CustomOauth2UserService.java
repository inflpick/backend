package com.leesh.inflpick.common.adapter.in.web.security;

import com.leesh.inflpick.user.adapter.in.web.Oauth2Type;
import com.leesh.inflpick.user.core.domain.Nickname;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
@Transactional
@Service
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
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

        Oauth2Type oauth2Type = Oauth2Type.from(registrationId);
        OAuth2User convertOauth2User = oauth2UserConverters.stream()
                .filter(converter -> converter.isSupport(oauth2Type))
                .findAny()
                .orElseThrow(() -> new NotSupportedOauth2TypeException(oauth2Type.name()))
                .convert(oAuth2User, userNameAttributeName);

        Oauth2UserInfo oauth2UserInfo = Oauth2UserInfo.of(
                convertOauth2User.getName(),
                oauth2Type);

        AtomicReference<String> userId = new AtomicReference<>();
        userQueryService.getOauth2User(oauth2UserInfo)
                .ifPresentOrElse(
                        user -> userId.set(user.getId()),
                        () -> {
                            String id = registerNewUser(convertOauth2User, oauth2UserInfo);
                            userId.set(id);
                        });
        User user = userQueryService.getById(userId.get());
        return new CustomOauth2User(user);
    }

    private String registerNewUser(OAuth2User convertOauth2User, Oauth2UserInfo oauth2UserInfo) {
        String nickname = convertOauth2User.getAttribute("nickname");
        String profileImageUrl = convertOauth2User.getAttribute("profileImageUrl");
        assert nickname != null;
        Nickname userNickname = Nickname.from(nickname);
        UserCommand command = UserCommand.builder()
                .nickname(userNickname)
                .profileImageUrl(profileImageUrl)
                .role(Role.ADMIN) // TODO 임시! 추후 USER 로 수정 필요!
                .oauth2UserInfo(oauth2UserInfo)
                .build();
        return userCommandService.create(command);
    }
}
