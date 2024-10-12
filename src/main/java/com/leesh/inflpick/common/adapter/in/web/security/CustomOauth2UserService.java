package com.leesh.inflpick.common.adapter.in.web.security;

import com.leesh.inflpick.user.adapter.in.web.Oauth2Type;
import com.leesh.inflpick.user.core.domain.Oauth2UserInfo;
import com.leesh.inflpick.user.core.domain.User;
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
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    private final UserQueryService userQueryService;
    private final List<Oauth2UserRegister> oauth2UserRegisters;

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
        Object attribute = oAuth2User.getAttribute(userNameAttributeName);
        Oauth2UserInfo oauth2UserInfo = Oauth2UserInfo.of(
                attribute == null ? "" : attribute.toString(),
                oauth2Type);

        Optional<User> optionalUser = userQueryService.getOauth2User(oauth2UserInfo);
        if (optionalUser.isEmpty()) {
            User newUser = oauth2UserRegisters.stream()
                    .filter(converter -> converter.isSupport(oauth2Type))
                    .findAny()
                    .orElseThrow(() -> new NotSupportedOauth2TypeException(oauth2Type.name()))
                    .register(oAuth2User, userNameAttributeName);
            return new CustomOauth2User(newUser);
        }

        User user = optionalUser.get();
        return new CustomOauth2User(user);
    }
}
