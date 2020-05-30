package com.moim.user.service.user;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.moim.user.entity.Address;
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
		
		@NotBlank
		private String username;
		
		@NotBlank
		private String password;
		
		@NotBlank
		private String userNm;
		
		@Valid
		private Address address;
		
		@NotBlank
		private String phone;
		
		@Builder
		public SignUpReq(String username, String password, String userNm, Address address, String phone) {
			this.username = username;
			this.password = password;
			this.userNm = userNm;
			this.address = address;
			this.phone = phone;
		}
		
		public User toEntity() {
			return User.builder()
					.username(username)
					.password(password)
					.userType(0)
					.userNm(userNm)
					.address(address)
					.phone(phone)
					.build();
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
		private Address address;
		private String phone;
	}
}
