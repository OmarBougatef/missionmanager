package com.example.softravelbackend.security;

import com.example.softravelbackend.service.UserInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final UserInfoService userInfoService;

    public SecurityConfig(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/users/**").hasRole("ADMIN")
                        .requestMatchers("/api/missions/**").hasAnyRole("ADMIN", "MANAGER", "COLLABORATEUR")
                        .requestMatchers("/api/liquidations/**").hasAnyRole("ADMIN", "MANAGER", "COLLABORATEUR")
                        .anyRequest().authenticated()
                )
                 .securityContext(securityContext -> securityContext.requireExplicitSave(false))
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userInfoService);
        return authProvider;
    }
}
