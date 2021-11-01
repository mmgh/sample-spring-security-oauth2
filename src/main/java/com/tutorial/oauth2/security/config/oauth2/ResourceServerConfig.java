package com.tutorial.oauth2.security.config.oauth2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	
	private static final Logger log = LoggerFactory.getLogger(ResourceServerConfig.class);
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests().anyRequest().authenticated()
			.and()
			.requestMatchers().antMatchers("/api/**");
		
	}

}
