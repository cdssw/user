package com.moim.user.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public long signUpUser(@RequestBody @Valid UserDto.SignUpReq dto) {
		return userService.signUpUser(dto);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public UserDto.Res getUser(@PathVariable final long id) {
		return userService.getUser(id);
	}
}
