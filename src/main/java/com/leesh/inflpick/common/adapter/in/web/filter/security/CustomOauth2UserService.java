package com.leesh.inflpick.common.adapter.in.web.filter.security;

import com.leesh.inflpick.user.application.port.out.CommandUserPort;
import com.leesh.inflpick.user.application.port.out.QueryUserPort;
import com.leesh.inflpick.user.domain.User;
import com.leesh.inflpick.user.domain.exception.NotSupportedOauth2TypeException;
import com.leesh.inflpick.user.domain.vo.Oauth2Info;
import com.leesh.inflpick.user.domain.vo.Oauth2Provider;
import com.leesh.inflpick.user.domain.vo.Role;
import com.leesh.inflpick.user.domain.vo.UserId;
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
class CustomOauth2UserService extends DefaultOAuth2UserService {

    private final CommandUserPort commandUserPort;
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

        User user = findUserOrRegisterNewUser(oauth2Info, convertOauth2User);
        return new CustomOauth2User(user);
    }

    private User findUserOrRegisterNewUser(Oauth2Info oauth2Info, OAuth2User convertOauth2User) {
        AtomicReference<User> user = new AtomicReference<>();
        queryUserPort.query(oauth2Info)
                .ifPresentOrElse(
                        user::set,
                        () -> {
                            UserId userId = registerNewUser(convertOauth2User, oauth2Info);
                            queryUserPort.query(userId).map(u -> {
                                user.set(u);
                                return u;
                            });
                        }
                );
        return user.get();
    }

    private UserId registerNewUser(OAuth2User convertOauth2User, Oauth2Info oauth2Info) {
        String nickname = convertOauth2User.getAttribute("nickname");
        String profileImageUrl = convertOauth2User.getAttribute("profileImageUrl");
        String email = convertOauth2User.getAttribute("email");
        User user = User.withoutId(nickname, oauth2Info.getId(), oauth2Info.getProvider(), profileImageUrl, Role.ADMIN, email);
        return commandUserPort.save(user);
    }
}
