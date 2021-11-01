package com.tutorial.oauth2.security.config.oauth2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	
	private static final Logger log = LoggerFactory.getLogger(AuthorizationServerConfig.class);
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		clients
			.inMemory()
			.withClient("client")
			.secret("{noop}secret")
			.redirectUris("http://localhost:8080/callback")
			.authorizedGrantTypes("authorization_code")
			.scopes("read_profile", "write_profile")
			.accessTokenValiditySeconds(1000*60*10)
			.refreshTokenValiditySeconds(1000*60*11)
			;
	}
	
}
