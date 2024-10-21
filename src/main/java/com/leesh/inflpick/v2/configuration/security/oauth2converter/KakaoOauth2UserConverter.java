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
public class KakaoOauth2UserConverter implements Oauth2UserConverter {

    @Override
    public Boolean isSupport(Oauth2Provider oauth2Provider) {
        return Oauth2Provider.KAKAO.equals(oauth2Provider);
    }

    @Override
    public OAuth2User convert(OAuth2User oAuth2User, String userNameAttributeName) {
        String nickname = "";
        String profileImageUrl = "";
        String email = "";

        Map<String, Object> attributes = oAuth2User.getAttributes();
        if (attributes.containsKey("kakao_account")) {
            Object kakaoAccountObj = attributes.get("kakao_account");
            if (kakaoAccountObj instanceof Map) {
                Map<String, Object> kakaoAccount = (Map<String, Object>) kakaoAccountObj;
                Object profileObj = kakaoAccount.getOrDefault("profile", Map.of());
                if (profileObj instanceof Map) {
                    Map<String, Object> profile = (Map<String, Object>) profileObj;
                    nickname = (String) profile.getOrDefault("nickname", nickname);
                    profileImageUrl = (String) profile.getOrDefault("profile_image_url", profileImageUrl);
                    email = (String) kakaoAccount.getOrDefault("email", email);
                }
            }
        }

        Map<String, Object> attr = oAuth2User.getAttributes();
        Object oauth2UserId = attr.get(userNameAttributeName);
        String finalEmail = email;
        String finalNickname = nickname;
        String finalProfileImageUrl = profileImageUrl;
        return new OAuth2User() {
            @Override
            public Map<String, Object> getAttributes() {
                return Map.of(
                        "id", oauth2UserId,
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
                return oauth2UserId.toString();
            }
        };
    }
}
