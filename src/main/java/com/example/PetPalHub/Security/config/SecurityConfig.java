package com.example.PetPalHub.Security.config;

import com.example.PetPalHub.Security.JWTAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JWTAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        c -> c
                                .requestMatchers(CommonConfigs.AUTH_WHITELIST)
                                .permitAll()
                                .requestMatchers(CommonConfigs.AUTH_AUTHENTICATED)
                                .authenticated()
                                .requestMatchers(CommonConfigs.AUTH_ADOPTER)
                                .hasRole("ADOPTER")
                                .requestMatchers(CommonConfigs.AUTH_MASTER)
                                .hasAnyRole("STAFF","MANAGER")
                                .anyRequest().permitAll()
                ).sessionManagement(
                        s -> s
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter.class
                ).logout(
                        l -> l.logoutUrl("/logout")
                );
        return http.build();
    }
}
