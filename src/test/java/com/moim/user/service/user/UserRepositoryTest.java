package com.moim.user.service.user;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.moim.user.config.JpaAuditingConfig;
import com.moim.user.entity.Policy;
import com.moim.user.entity.User;
import com.moim.user.repository.UserRepository;

/**
 * UserRepositoryTest.java
 * 
 * @author cdssw
 * @since 2020. 7. 31.
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 2020. 7. 31.    cdssw            최초 생성
 * </pre>
 */
@RunWith(SpringRunner.class)
@DataJpaTest(includeFilters = @Filter(
		type = FilterType.ASSIGNABLE_TYPE,
		classes = {JpaAuditingConfig.class}))
@ActiveProfiles("test")
public class UserRepositoryTest {
	
	@Autowired
	UserRepository userRepository;
	
	@Before
	public void setUp() {
		userRepository.deleteAll();
	}
	
	@After
	public void cleanup() {
		userRepository.deleteAll();
	}
	
	@Test
	public void testExistsUsername() {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		// given
		User user = User.builder()
				.username("cdssw@naver.com")
				.password(passwordEncoder.encode("1234"))
				.userType(0)
				.userNm("Andrew")
				.userNickNm("Blue")
				.phone("010-1111-1111")
				.mainTalent("프로그램 개발")
				.talent("자바,웹 개발,MSA,파이썬")
				.interest("음악,영상")
				.avatarPath("/file/test.jpg")
				.policy(Policy.builder().serviceYn(true).privateYn(true).profileYn(true).build())
				.build();
		userRepository.save(user);
		
		// when
		boolean exists = userRepository.existsByUsername("cdssw@naver.com");
		
		// then
		assertTrue(exists);
	}
	
	@Test
	public void testSignup() {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		// given
		User user = User.builder()
				.username("tester@naver.com")
				.password(passwordEncoder.encode("1234"))
				.userType(0)
				.userNm("Andrew")
				.userNickNm("Blue")
				.phone("010-1111-1111")
				.mainTalent("프로그램 개발")
				.talent("자바,웹 개발,MSA,파이썬")
				.interest("음악,영상")
				.avatarPath("/file/test.jpg")
				.policy(Policy.builder().serviceYn(true).privateYn(true).profileYn(true).build())
				.build();
		userRepository.save(user);
		
		// when
		User rst = userRepository.findByUsername("tester@naver.com");
		
		// then
		assertTrue(rst.getPolicy().isServiceYn());
	}
}
