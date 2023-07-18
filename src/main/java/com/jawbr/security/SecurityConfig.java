package com.jawbr.security;

import com.jawbr.security.jwt.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(config ->
                config
                        .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                        // Magic Items
                        .requestMatchers(HttpMethod.GET,"/api/magic-items", "/api/magic-items/{indexName}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/magic-items/id/{id}").hasRole("ADMIN")
                        // Source Book
                        .requestMatchers(HttpMethod.GET, "/api/source-books", "/api/source-books/{indexName}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/source-books/id/{id}").hasRole("ADMIN")
                        // Equip Cat
                        .requestMatchers(HttpMethod.GET, "/api/equipment-categories", "/api/equipment-categories/{indexName}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/equipment-categories/id/{id}").hasRole("ADMIN")
                        .anyRequest().hasRole("ADMIN")
        );

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter();
    }
}
