package com.leesh.inflpick.v2.configuration.security.oauth2converter;

import com.leesh.inflpick.v2.configuration.security.Oauth2UserConverter;
import com.leesh.inflpick.v2.user.domain.vo.Oauth2Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class NaverOauth2UserConverter implements Oauth2UserConverter {

    @Override
    public Boolean isSupport(Oauth2Provider oauth2Provider) {
        return Oauth2Provider.NAVER.equals(oauth2Provider);
    }

    @Override
    public OAuth2User convert(OAuth2User oAuth2User, String userNameAttributeName) {
        String oauth2UserId = "";
        String nickname = "";
        String profileImageUrl = "";
        String email = "";

        Map<String, Object> attributes = oAuth2User.getAttributes();
        if (attributes.containsKey("response")) {
            Object naverResponseObj = attributes.get("response");
            if (naverResponseObj instanceof Map) {
                Map<String, Object> naverResponse = (Map<String, Object>) naverResponseObj;
                nickname = (String) naverResponse.getOrDefault("nickname", nickname);
                profileImageUrl = (String) naverResponse.getOrDefault("profile_image", profileImageUrl);
                email = (String) naverResponse.getOrDefault("email", email);
                oauth2UserId = (String) naverResponse.getOrDefault("id", oauth2UserId);
            }
        }

        String finalOauth2UserId = oauth2UserId;
        String finalEmail = email;
        String finalNickname = nickname;
        String finalProfileImageUrl = profileImageUrl;
        return new OAuth2User() {
            @Override
            public Map<String, Object> getAttributes() {
                return Map.of(
                        "id", finalOauth2UserId,
                        "email", finalEmail,
                        "nickname", finalNickname,
                        "profileImageUrl", finalProfileImageUrl
                );
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }

            @Override
            public String getName() {
                return finalOauth2UserId;
            }
        };
    }
}
