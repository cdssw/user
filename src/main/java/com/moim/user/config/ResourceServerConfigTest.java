package com.moim.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * ResourceServerConfigTest.java
 * 
 * @author cdssw
 * @since 2020. 5. 18.
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 2020. 5. 18.    cdssw            최초 생성
 * </pre>
 */
@EnableResourceServer
@Configuration
@Profile("test")
public class ResourceServerConfigTest extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().permitAll();
	}
}
