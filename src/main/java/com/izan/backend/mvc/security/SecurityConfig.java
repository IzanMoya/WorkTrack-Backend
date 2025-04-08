package com.izan.backend.mvc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll() // Permite acceso sin autenticación a /auth/**
                        .anyRequest().authenticated() // Todas las demás rutas requieren autenticación
                )
                .addFilter(new FirebaseAuthenticationFilter());

        return http.build();
    }
}
