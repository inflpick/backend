package com.leesh.inflpick.v2.adapter.in.web.common.security;

import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.UserId;
import com.leesh.inflpick.v2.appilcation.port.in.user.GetUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final GetUserUseCase userUseCase;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserId userId = UserId.create(username);
        User user = userUseCase.getUser(userId);
        return new CustomUserDetails(user);
    }
}
