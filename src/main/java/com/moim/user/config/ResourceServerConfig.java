package com.moim.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * ResourceServerConfig.java
 * 
 * @author cdssw
 * @since 2020. 5. 15.
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 2020. 5. 15.   cdssw            최초 생성
 * </pre>
 */
@Profile({"dev", "prod"})
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true) // PreAuthorize 어노테이션 활성화
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String[] WHITE_LIST = {
			"/v2/api-docs",
			"/swagger-resources",
			"/swagger-resources/**",
			"/swagger-ui.html",
			"/webjars/**",
			"/h2-console/**",
			"/*/v2/api-docs",
			"/check/**"
	};
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable(); // X-Frame-Options 차단 해제
		http.authorizeRequests()
			.antMatchers(WHITE_LIST).permitAll()
			.antMatchers(HttpMethod.POST, "/signup/").permitAll() // 회원가입은 허용
			.anyRequest().authenticated(); // 모든 요청 호출시 인증되어야 함
	}
}
