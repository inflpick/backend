package com.leesh.inflpick.common.adapter.in.web.security;


import com.leesh.inflpick.common.adapter.in.web.value.CorsProperties;
import com.leesh.inflpick.user.adapter.out.jwt.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@EnableConfigurationProperties({CorsProperties.class})
@RequiredArgsConstructor
@EnableMethodSecurity()
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final UserDetailsService userDetailsService;
    private final DefaultOAuth2UserService oAuth2UserService;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;
    private final CorsConfigurationSource corsConfigurationSource;
    private final JwtProperties jwtProperties;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)) // OAuth2UserService 사용 시, 유저 정보 저장을 위해 세션 사용
                .formLogin(AbstractHttpConfigurer::disable)
                .authenticationManager(authenticationManager(http))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/resources/**").permitAll()
                        .requestMatchers("/",
                                "/swagger-ui/**", "/v3/api-docs/**",
                                "/oauth2/**", "/loginSuccess",
                                "/actuator/health").permitAll()
                        .requestMatchers(HttpMethod.GET, "/influencers/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/keywords/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/reviews/**").permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(errors -> errors
                        .accessDeniedHandler(accessDeniedHandler)
                        .authenticationEntryPoint(authenticationEntryPoint))
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/loginSuccess")
                        .failureUrl("/loginFailure")
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oAuth2UserService) // 일반 OAuth 사용자 정보를 처리
                        ));

        http.addFilterBefore(jwtAuthenticationFilter(http), BasicAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .authenticationProvider(authenticationProvider)
                .userDetailsService(userDetailsService);
        return authenticationManagerBuilder.build();
    }

    public JwtAuthenticationFilter jwtAuthenticationFilter(HttpSecurity httpSecurity) throws Exception {
        return new JwtAuthenticationFilter(authenticationManager(httpSecurity), jwtProperties);
    }

}
