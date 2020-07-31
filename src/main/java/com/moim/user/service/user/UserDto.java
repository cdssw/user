package com.moim.user.service.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.moim.user.entity.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * UserDto.java
 * 
 * @author cdssw
 * @since Apr 18, 2020
 * @description User Rest DTO
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 18, 2020   cdssw            최초 생성
 * </pre>
 */
public class UserDto {

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SignUpReq {
		
		@Email
		@NotBlank
		private String username;
		
		@NotBlank
		private String password;
		
		@NotBlank
		private String userNm;
		
		@NotBlank
		private String userNickNm;
		
		@NotBlank
		private String phone;
		
		private String mainTalent;
		
		private String talent;
		
		private String interest;
		
		@Builder
		public SignUpReq(String username, String password, String userNm, String userNickNm, String phone, String mainTalent, String talent, String interest) {
			this.username = username;
			this.password = password;
			this.userNm = userNm;
			this.userNickNm = userNickNm;
			this.phone = phone;
			this.mainTalent = mainTalent;
			this.talent = talent;
			this.interest = interest;
		}
		
		public User toEntity() {
			return User.builder()
					.username(username)
					.password(password)
					.userType(0)
					.userNm(userNm)
					.userNickNm(userNickNm)
					.phone(phone)
					.mainTalent(mainTalent)
					.talent(talent)
					.interest(interest)
					.build();
		}
	}
	
	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class UserReq {
		
		@NotBlank
		private String phone;
		
		@Builder
		public UserReq(String phone) {
			this.phone = phone;
		}
	}
	
	@Getter
	@Setter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Res {
		private String username;
		private String userNm;
		private String userNick;
		private String phone;
		private String mainTalent;
		private String talent;
		private String interest;
	}
}
