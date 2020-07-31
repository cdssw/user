package com.moim.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.moim.user.service.user.UserDto;
import com.moim.user.service.user.UserService;

import lombok.AllArgsConstructor;

/**
 * UserController.java
 * 
 * @author cdssw
 * @since 5. 30, 2020
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 5. 30, 2020    cdssw            최초 생성
 * </pre>
 */
@AllArgsConstructor
@RestController
@RequestMapping
public class UserController {

	private UserService userService;
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public UserDto.Res getUser(HttpServletRequest req) {
		String username = req.getHeader("username"); // gateway에서 보내준 username header를 추출
		return userService.getUser(username);
	}
	
	@PostMapping("/signup")
	@ResponseStatus(value = HttpStatus.CREATED)
	public long signUpUser(@RequestBody @Valid UserDto.SignUpReq dto) {
		return userService.signUpUser(dto);
	}
	
	@PreAuthorize("#oauth2.hasScope('write')") // write scope로 제한
	@PutMapping
	@ResponseStatus(value = HttpStatus.OK)
	public UserDto.Res editUser(@RequestBody @Valid final UserDto.UserReq dto, HttpServletRequest req) {
		String username = req.getHeader("username"); // gateway에서 보내준 username header를 추출
		return userService.editUser(username, dto);
	}
	
	@GetMapping("/check/username")
	@ResponseStatus(value = HttpStatus.OK)
	public int checkUsername(@RequestParam("username") final String username) {
		return userService.existsUser(username) ? 1 : 0;
	}
	
	@GetMapping("/check/nicknm")
	@ResponseStatus(value = HttpStatus.OK)
	public int checkNicknm(@RequestParam("nicknm") final String userNickNm) {
		return userService.existsNickNm(userNickNm) ? 1 : 0;
	}	
}
