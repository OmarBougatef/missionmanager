package com.example.softravelbackend.security;

import com.example.softravelbackend.service.UserInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserInfoService userInfoService;

    public SecurityConfig(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));  // Allowing frontend origin
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);  // Allow credentials (cookies)
        CorsConfigurationSource source = request -> configuration;
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))  // Use the CorsConfigurationSource bean
                .csrf(csrf -> csrf.disable())  // Disable CSRF
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/api/auth/**").permitAll() // Allow authentication endpoints
                        .requestMatchers("/api/users/**").hasRole("ADMIN")
                        .requestMatchers("/api/missions/manager/**").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers("/api/missions/**").hasAnyRole("ADMIN", "MANAGER", "COLLABORATEUR")
                        .requestMatchers("/api/liquidations/**").hasAnyRole("ADMIN", "MANAGER", "COLLABORATEUR")
                        .anyRequest().authenticated()
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.toString().equals(encodedPassword);
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userInfoService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
