package com.leesh.inflpick.auth.v2.adapter.security;

import com.leesh.inflpick.user.port.out.NotSupportedOauth2TypeException;
import com.leesh.inflpick.user.v2.core.entity.User;
import com.leesh.inflpick.user.v2.core.entity.vo.*;
import com.leesh.inflpick.user.v2.port.out.QueryUserPort;
import com.leesh.inflpick.user.v2.port.in.CreateSocialUserUseCase;
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

    private final CreateSocialUserUseCase createSocialUserUseCase;
    private final QueryUserPort queryUserPort;
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

        AtomicReference<UserId> userId = new AtomicReference<>();
        queryUserPort.query(oauth2Info)
                .ifPresentOrElse(
                        user -> userId.set(user.getId()),
                        () -> {
                            UserId id = registerNewUser(convertOauth2User, oauth2Info);
                            userId.set(id);
                        });
        User user = queryUserPort.query(userId.get());
        return new CustomOauth2User(user);
    }

    private UserId registerNewUser(OAuth2User convertOauth2User, Oauth2Info oauth2Info) {
        String nickname = convertOauth2User.getAttribute("nickname");
        String profileImageUrl = convertOauth2User.getAttribute("profileImageUrl");
        String email = convertOauth2User.getAttribute("email");
        return createSocialUserUseCase.create(() -> {
            Nickname userNickname = Nickname.create(nickname);
            UserEmail userEmail = UserEmail.create(email);
            return User.builder(userNickname, oauth2Info)
                    .email(userEmail)
                    .profileImageUrl(profileImageUrl)
                    .role(Role.ADMIN)
                    .build();
        });
    }
}
