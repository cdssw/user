package com.moim.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.moim.user.service.hopeplace.HopePlaceDto;
import com.moim.user.service.hopeplace.HopePlaceService;

import lombok.AllArgsConstructor;

/**
 * HopePlaceController.java
 * 
 * @author cdssw
 * @since 2020. 10. 15.
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 2020. 10. 15.    cdssw            최초 생성
 * </pre>
 */
@AllArgsConstructor
@RestController
@RequestMapping("/hopeplace")
public class HopePlaceController {
	
	private HopePlaceService hopePlaceService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public long createHopePlace(@RequestBody @Valid HopePlaceDto.HopePlaceReq dto, HttpServletRequest req) {
		String username = req.getHeader("username"); // gateway에서 보내준 username header를 추출
		return hopePlaceService.createHopePlace(username, dto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteHopePlace(@PathVariable final long id, HttpServletRequest req) {
		String username = req.getHeader("username"); // gateway에서 보내준 username header를 추출
		hopePlaceService.deleteHopePlace(id, username);
	}
}
