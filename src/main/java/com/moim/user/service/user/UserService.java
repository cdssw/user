package com.moim.user.service.user;

/**
 * UserService.java
 * 
 * @author cdssw
 * @since Apr 18, 2020
 * @description Controller에 제공되는 서비스 
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 18, 2020   cdssw            최초 생성
 * </pre>
 */
public interface UserService {

	long signUpUser(UserDto.SignUpReq dto);
	UserDto.Res getUser(final String username);
	UserDto.Res editUser(final String username, UserDto.UserReq dto);
	boolean existsUser(final String username);
	boolean existsNickNm(final String userNickNm);
	void passwordChange(final String username, final UserDto.PasswordChangeReq dto);
	String getUserAvatar(final String username);
}
