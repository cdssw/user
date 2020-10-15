package com.moim.user.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.moim.user.service.user.UserDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * User.java
 * 
 * @author cdssw
 * @since Apr 12, 2020
 * @description 
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 12, 2020   cdssw            최초 생성
 * </pre>
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 20, unique = true, nullable = false)
    private String username;
	
    @Column(length = 400, nullable = false)
    private String password;
    
    @Column(nullable = false)
    private int userType;

	private String userNm;
	
	private String userNickNm;
	
	private String phone;
	
	private String mainTalent;
	
	private String talent;
	
	private String interest;
	
	private String avatarPath;
	
	@Builder
	public User(String username
			, String password
			, int userType
			, String userNm
			, String userNickNm
			, String phone
			, String mainTalent
			, String talent
			, String interest
			, String avatarPath
			) {
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.userNm = userNm;
		this.userNickNm = userNickNm;
		this.phone = phone;
		this.mainTalent = mainTalent;
		this.talent = talent;
		this.interest = interest;
		this.avatarPath = avatarPath;
	}
	
	// 사용자 정보 변경
	public void editUser(UserDto.UserReq dto) {
		this.phone = dto.getPhone() == null ? phone : dto.getPhone();
		this.mainTalent = dto.getMainTalent() == null ? mainTalent : dto.getMainTalent();
		this.talent = dto.getTalent() == null ? talent : dto.getTalent();
		this.interest = dto.getInterest() == null ? interest : dto.getInterest();
		this.avatarPath = dto.getAvatarPath() == null ? avatarPath : dto.getAvatarPath();
	}
	
	// 비밀번호 변경
	public void changePassword(String password) {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		this.password = passwordEncoder.encode(password);
	}
}
