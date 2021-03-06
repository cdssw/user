package com.moim.user.service.user;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.moim.kafka.EventUser;
import com.moim.user.entity.User;
import com.moim.user.event.Sender;
import com.moim.user.except.ErrorCode;
import com.moim.user.except.UserBusinessException;
import com.moim.user.repository.HopePlaceRepository;
import com.moim.user.repository.UserRepository;
import com.moim.user.service.user.UserDto.ApplicatorRes;
import com.moim.user.service.user.UserDto.PasswordChangeReq;
import com.moim.user.service.user.UserDto.Res;
import com.moim.user.service.user.UserDto.SignUpReq;
import com.moim.user.service.user.UserDto.UserReq;

import lombok.AllArgsConstructor;
/**
 * UserServiceImpl.java
 * 
 * @author cdssw
 * @since Apr 18, 2020
 * @description 서비스 구현체 
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 18, 2020   cdssw            최초 생성
 * </pre>
 */
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	
	private ModelMapper modelMapper;
	private UserRepository userRepository;
	private HopePlaceRepository hopePlaceRepository;
	private Sender sender;
	
	private static String topicUserModified;
	@Value("${spring.kafka.topic.user-modified}")
	private void setTopicUserModified(String topic) {
		topicUserModified = topic;
	}
	
	private static String topicUserCreated;
	@Value("${spring.kafka.topic.user-created}")
	private void setTopicUserCreated(String topic) {
		topicUserCreated = topic;
	}
	
	@Transactional
	@Override
	public long signUpUser(SignUpReq dto) {
		User user = dto.toEntity();
		user.changePassword(user.getPassword());
		user = userRepository.save(user);
		sender.send(topicUserCreated, modelMapper.map(user, EventUser.class)); // kafka pub
		return user.getId();
	}

	@Override
	public Res getUser(final String username) {
		User user = userRepository.findByUsername(username);
		Res res = modelMapper.map(user, Res.class);
		res.setHopePlaceList(hopePlaceRepository.findByUser(user));
		return res;
	}

	@Transactional
	@Override
	public Res editUser(String username, UserReq dto) {
		User user = userRepository.findByUsername(username);
		user.editUser(dto);
		sender.send(topicUserModified, modelMapper.map(user, EventUser.class)); // kafka pub

		return modelMapper.map(user, UserDto.Res.class);
	}

	@Override
	public boolean existsUser(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public boolean existsNickNm(String userNickNm) {
		return userRepository.existsByUserNickNm(userNickNm);
	}

	@Transactional
	@Override
	public void passwordChange(String username, PasswordChangeReq dto) {
		User user = userRepository.findByUsername(username);
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		if(!passwordEncoder.matches(dto.getCurrentPassword(), user.getPassword())) {
			throw new UserBusinessException(ErrorCode.INVALID_PASSWORD);
		}
		user.changePassword(dto.getPassword());
	}

	@Override
	public String getUserAvatar(String username) {
		User user = userRepository.findByUsername(username);
		return user.getAvatarPath();
	}

	@Override
	public ApplicatorRes getApplicator(String username) {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UserBusinessException(ErrorCode.USER_NOT_FOUND);
		}
		
		return modelMapper.map(user, UserDto.ApplicatorRes.class);
	}
}
