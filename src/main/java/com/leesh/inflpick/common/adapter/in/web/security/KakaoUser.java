package com.leesh.inflpick.common.adapter.in.web.security;

import com.leesh.inflpick.user.core.domain.Nickname;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public record KakaoUser(String id, String nickname, String profileImageUrl, String email) implements OAuth2User {

    @Override
    public Map<String, Object> getAttributes() {
        Nickname nickname = Nickname.from(this.nickname);
        return Map.of(
                "id", id,
                "nickname", nickname,
                "profile_image_url", profileImageUrl,
                "email", email
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getName() {
        return id;
    }

    public static KakaoUser from(OAuth2User oAuth2User, String userNameAttributeName) {

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
                    profileImageUrl = (String) profile.getOrDefault("thumbnail_image_url", profileImageUrl);
                    email = (String) kakaoAccount.getOrDefault("email", email);
                }
            }
        }

        Object attribute = oAuth2User.getAttribute(userNameAttributeName);
        return new KakaoUser(
                attribute == null ? "" : attribute.toString(),
                nickname,
                profileImageUrl,
                email
        );
    }
}
