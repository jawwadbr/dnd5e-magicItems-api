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

@Configuration
@EnableWebSecurity
public class ApiSecurityConfig {
	
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails ADMIN = User.withUsername("admin").password("{noop}admin").roles("ADMIN").build();
		
		return new InMemoryUserDetailsManager(ADMIN);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// should learn more about csrf 
		http.csrf().disable().authorizeHttpRequests().requestMatchers(HttpMethod.POST, "/api/magic-items").hasRole("ADMIN")
									.anyRequest().permitAll().and().httpBasic();
		return http.build();
	}
}
