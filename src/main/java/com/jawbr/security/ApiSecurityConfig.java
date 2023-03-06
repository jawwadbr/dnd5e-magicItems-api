package com.jawbr.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class ApiSecurityConfig {
	
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		// In memory user for testing
		UserDetails ADMIN = User.withUsername("admin").password("{noop}admin").roles("ADMIN").build();
		
		return new InMemoryUserDetailsManager(ADMIN);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// should learn more about csrf 
		http.authorizeHttpRequests()
				.requestMatchers(HttpMethod.GET, "/api/magic-items/id/{magicItemId}").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/api/magic-items").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/magic-items/{magicItemId}").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/magic-items").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/api/magic-items").hasRole("ADMIN") // temp, need DELETE endpoint first
				.anyRequest().permitAll()
			.and()
				.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).ignoringRequestMatchers("/api/magic-items/**").ignoringRequestMatchers("/api/magic-items")
			.and()
				.httpBasic()
			.and()
				.formLogin();
		
		return http.build();
	}
}
