package com.leesh.inflpick.common.adapter.in.web.filter.security;

import com.leesh.inflpick.user.application.exception.UserNotFoundException;
import com.leesh.inflpick.user.application.port.out.QueryUserPort;
import com.leesh.inflpick.user.domain.User;
import com.leesh.inflpick.user.domain.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CustomUserDetailsService implements UserDetailsService {

    private final QueryUserPort queryUserPort;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserId userId = UserId.create(username);
        User user = queryUserPort.query(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return new CustomUserDetails(user);
    }
}
