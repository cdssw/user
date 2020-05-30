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

	Long signUpUser(UserDto.SignUpReq dto);
	UserDto.Res getUser(long userId);
}
