package com.tutorial.oauth2.security.config.oauth2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	
	private static final Logger log = LoggerFactory.getLogger(AuthorizationServerConfig.class);

    
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
            .userDetailsService(userDetailsService);
    }
	
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		clients
			.inMemory()
			.withClient("hone-wiki")
			.secret("{noop}hone-wiki-secret")
			.redirectUris("http://localhost:8080/callback")
			.authorizedGrantTypes("authorization_code", "refresh_token")
			.scopes("read_user", "create_user")
			.accessTokenValiditySeconds(1000*60*10)
			.refreshTokenValiditySeconds(1000*60*11)
			;
		
	}
	
}
