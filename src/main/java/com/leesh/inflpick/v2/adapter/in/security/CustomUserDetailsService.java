package com.leesh.inflpick.v2.adapter.in.security;

import com.leesh.inflpick.v2.appilcation.port.in.user.QueryUserUseCase;
import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.UserId;
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
