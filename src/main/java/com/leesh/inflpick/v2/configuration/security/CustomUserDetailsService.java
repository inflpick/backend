package com.leesh.inflpick.v2.configuration.security;

import com.leesh.inflpick.v2.user.application.port.in.QueryUserUseCase;
import com.leesh.inflpick.v2.user.domain.User;
import com.leesh.inflpick.v2.user.domain.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CustomUserDetailsService implements UserDetailsService {

    private final QueryUserUseCase queryUserUseCase;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserId userId = UserId.create(username);
        User user = queryUserUseCase.query(userId);
        return new CustomUserDetails(user);
    }
}
