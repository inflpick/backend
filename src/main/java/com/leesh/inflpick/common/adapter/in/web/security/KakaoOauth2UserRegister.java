package com.leesh.inflpick.common.adapter.in.web.security;

import com.leesh.inflpick.user.adapter.in.web.Oauth2Type;
import com.leesh.inflpick.user.core.domain.Nickname;
import com.leesh.inflpick.user.core.domain.Oauth2UserInfo;
import com.leesh.inflpick.user.core.domain.Role;
import com.leesh.inflpick.user.core.domain.User;
import com.leesh.inflpick.user.port.in.UserCommand;
import com.leesh.inflpick.user.port.in.UserCommandService;
import com.leesh.inflpick.user.port.in.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class KakaoOauth2UserRegister implements Oauth2UserRegister {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @Override
    public Boolean isSupport(Oauth2Type oauth2Type) {
        return Oauth2Type.KAKAO.equals(oauth2Type);
    }

    @Override
    public User register(OAuth2User oAuth2User, String userNameAttributeName) {

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

        String oauth2UserId = oAuth2User.getAttribute(userNameAttributeName);
        Oauth2UserInfo oauth2UserInfo = Oauth2UserInfo.of(
                oauth2UserId,
                Oauth2Type.KAKAO);

        Nickname userNickname = Nickname.from(nickname);
        UserCommand command = UserCommand.builder()
                .nickname(userNickname)
                .profileImageUrl(profileImageUrl)
                .role(Role.USER)
                .oauth2UserInfo(oauth2UserInfo)
                .build();

        String id = userCommandService.create(command);
        return userQueryService.getById(id);
    }
}
