package com.leesh.inflpick.v2.adapter.in.security.oauth2converter;

import com.leesh.inflpick.v2.adapter.in.security.Oauth2UserConverter;
import com.leesh.inflpick.v2.domain.user.vo.Oauth2Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class GoogleOauth2UserConverter implements Oauth2UserConverter {

    @Override
    public Boolean isSupport(Oauth2Provider oauth2Provider) {
        return Oauth2Provider.GOOGLE.equals(oauth2Provider);
    }

    @Override
    public OAuth2User convert(OAuth2User oAuth2User, String userNameAttributeName) {

        Map<String, Object> attributes = oAuth2User.getAttributes();
        final String nickname = (String) attributes.get("name");
        final String profileImageUrl = (String) attributes.get("picture");
        final String email = (String) attributes.get("email");

        Map<String, Object> attr = oAuth2User.getAttributes();
        Object oauth2UserId = attr.get(userNameAttributeName);
        return new OAuth2User() {
            @Override
            public Map<String, Object> getAttributes() {
                return Map.of(
                        "id", oauth2UserId,
                        "email", email,
                        "nickname", nickname,
                        "profileImageUrl", profileImageUrl
                );
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }

            @Override
            public String getName() {
                return (String) oauth2UserId;
            }
        };
    }
}
