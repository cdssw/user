package com.moim.user.component;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.moim.user.entity.Address;
import com.moim.user.entity.User;
import com.moim.user.repository.UserRepository;

import lombok.AllArgsConstructor;

/**
 * DataInitializer.java
 * 
 * @author cdssw
 * @since 2020. 5. 31.
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 2020. 5. 31.   cdssw            최초 생성
 * </pre>
 */
@AllArgsConstructor
@Component
public class DataInitializer implements ApplicationRunner {

	private UserRepository userRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		if(userRepository.findByUsername("cdssw@naver.com") == null) {
			PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			User user = User.builder()
					.username("cdssw@naver.com")
					.password(passwordEncoder.encode("1234"))
					.userType(0)
					.userNm("Andrew")
					.address(Address.builder().address("Seoul").addressDetail("Kang-nam").build())
					.phone("010-1111-1111")
					.build();
			userRepository.save(user);
		}
		
		if(userRepository.findByUsername("loh002@naver.com") == null) {
			PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			User user = User.builder()
					.username("loh002@naver.com")
					.password(passwordEncoder.encode("1234"))
					.userType(0)
					.userNm("Monica")
					.address(Address.builder().address("Seoul").addressDetail("Song-pa").build())
					.phone("010-2222-2222")
					.build();
			userRepository.save(user);
		}
		
		if(userRepository.findByUsername("michael@naver.com") == null) {
			PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			User user = User.builder()
					.username("michael@naver.com")
					.password(passwordEncoder.encode("1234"))
					.userType(0)
					.userNm("Michael")
					.address(Address.builder().address("Seoul").addressDetail("Seo-cho").build())
					.phone("010-3333-3333")
					.build();
			userRepository.save(user);
		}
	}

}
