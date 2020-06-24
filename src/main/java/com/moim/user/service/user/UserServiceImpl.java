package com.moim.user.service.user;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.moim.kafka.EventUser;
import com.moim.user.entity.User;
import com.moim.user.event.Sender;
import com.moim.user.repository.UserRepository;
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

}
