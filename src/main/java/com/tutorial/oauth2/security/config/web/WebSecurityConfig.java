package com.tutorial.oauth2.security.config.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	private static final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http
			.csrf().disable()
			.authorizeRequests().anyRequest().authenticated()
			.and()
			.formLogin()
			.and()
			.httpBasic();
			
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("admin").password("{noop}admin123").roles("USER", "ADMIN").build());
		manager.createUser(User.withUsername("user") .password("{noop}user123") .roles("USER").build());
		return manager;
	}
	
	
	
}
