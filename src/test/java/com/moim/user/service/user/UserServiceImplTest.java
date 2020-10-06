package com.moim.user.service.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.moim.user.entity.User;
import com.moim.user.event.Sender;
import com.moim.user.repository.UserRepository;
import com.moim.user.service.user.UserDto.PasswordChangeReq;

/**
 * UserServiceImplTest.java
 * 
 * @author cdssw
 * @since Apr 18, 2020
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 18, 2020   cdssw            최초 생성
 * </pre>
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
	
	// 테스트 하고자 하는 class
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private Sender sender;
	
	private UserDto.SignUpReq dto = null;
	private User user = null;

	private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	
	@Before
	public void setUp() {
		ModelMapper modelMapper = new ModelMapper();
		userServiceImpl = new UserServiceImpl(modelMapper, userRepository, sender);
		dto = UserDto.SignUpReq.builder()
				.username("cdssw@naver.com")
				.password("1234")
				.userNm("Andrew")
				.phone("010-1111-1111")
				.build();
		user = User.builder()
				.username("cdssw@naver.com")
				.password(passwordEncoder.encode("1234"))
				.userNm("Andrew")
				.phone("010-1111-1111")
				.avatarPath("/avatar/path")
				.build();
	}

	@Test
	public void testSignUpUser() {
		// given
		User user = mock(User.class);
		given(userRepository.save(any(User.class))).willReturn(user);
		given(user.getId()).willReturn(1L);
		
		// when
		Long userId = userServiceImpl.signUpUser(dto);
		
		// then
		assertEquals(userId, 1L);
	}
	
	@Test
	public void testGetUser() {
		// given
		given(userRepository.findByUsername(any())).willReturn(dto.toEntity());
		
		// when
		UserDto.Res res = userServiceImpl.getUser("cdssw@naver.com");
		
		// then
		assertThat(res.getUserNm()).isEqualTo(dto.getUserNm());
	}

	@Test
	public void testEditUser() {
		// given
		given(userRepository.findByUsername(any())).willReturn(dto.toEntity());
		UserDto.UserReq req = UserDto.UserReq.builder()
				.phone("010-9999-9999")
				.build();
		
		// when
		UserDto.Res res = userServiceImpl.editUser("cdssw@naver.com", req);
		
		// then
		verify(sender).send(any(), any());
		assertEquals(res.getPhone(), req.getPhone());
	}
	
	@Test
	public void testExistsUsername() {
		// given
		given(userRepository.existsByUsername(any())).willReturn(true);
		
		// when
		boolean exists = userServiceImpl.existsUser("cdssw@naver.com");
		
		// then
		assertEquals(exists, true);
	}
	
	@Test
	public void testExistsUserNickNm() {
		// given
		given(userRepository.existsByUserNickNm(any())).willReturn(true);
		
		// when
		boolean exists = userServiceImpl.existsNickNm("Blue");
		
		// then
		assertEquals(exists, true);
	}
	
	@Test
	public void testPasswordChange() {
		// given
		given(userRepository.findByUsername(any())).willReturn(user);
		PasswordChangeReq dto = PasswordChangeReq.builder()
				.currentPassword("1234")
				.password("qwer")
				.build();
		
		// when
		userServiceImpl.passwordChange("cdssw@naver.com", dto);
	}
	
	@Test
	public void testGetUserAvatar() {
		// given
		given(userRepository.findByUsername(any())).willReturn(user);
		
		// when
		String avatarPath = userServiceImpl.getUserAvatar("cdssw@naver.com");
		
		// then
		assertEquals(avatarPath, user.getAvatarPath());
	}	
}
