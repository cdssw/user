package com.moim.user.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moim.user.entity.Address;
import com.moim.user.service.user.UserDto;

import lombok.extern.slf4j.Slf4j;

/**
 * UserControllerTest.java
 * 
 * @author cdssw
 * @since 5. 30, 2020
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 5. 30, 2020   cdssw            최초 생성
 * </pre>
 */
@RunWith(SpringRunner.class)
@WebMvcTest // controller 관련 bean만 로딩
@Slf4j
public class UserControllerTest extends BaseControllerTest {

	private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext ctx;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private UserDto.SignUpReq dto;
	private UserDto.Res res1;
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
				.addFilters(new CharacterEncodingFilter("UTF-8", true)) // 한글깨짐 방지 처리
				.alwaysDo(print()) // 항상 결과 print
				.build();
		
		dto = UserDto.SignUpReq.builder()
				.username("cdssw@naver.com")
				.password("1234")
				.userNm("Andrew")
				.address(Address.builder().address("Seoul").addressDetail("Kang-nam").build())
				.phone("010-1111-1111")
				.build();
		res1 = UserDto.Res.builder()
				.username("cdssw@naver.com")
				.userNm("Andrew")
				.address(Address.builder().address("Seoul").addressDetail("Kang-nam").build())
				.phone("010-1111-1111")
				.build();
	}
	
	// 테스트 하는것은 dto를 가지고 controller 호출이 잘 되는지 확인
	@Test
	public void testSingUpUser() throws Exception {
		// given
		// 서비스 호출시 무조건 1L 리턴
		given(userService.signUpUser(any(UserDto.SignUpReq.class))).willReturn(1L);
		
		// when
		final MvcResult result = mvc.perform(post("/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isCreated()) // 201 확인
				.andReturn();
		
		String content = result.getResponse().getContentAsString();
		
		// assert
		assertEquals(content, "1");
	}
	
	// 테스트 하는것은 dto를 가지고 controller 호출시 valid 처리가 잘 되는지 확인
	@Test
	public void testSignUpUserValidExcept() throws Exception {
		// given
		UserDto.SignUpReq dto = UserDto.SignUpReq.builder().username("cdssw@naver.com").build();
		
		// when
		final MvcResult result = mvc.perform(post("/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isBadRequest()) // bad-request(400) 확인
				.andReturn();
		
		// then
		log.error(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testGetUser() throws Exception {
		// given
		given(userService.getUser(anyLong())).willReturn(res1);
		
		// when
		final MvcResult result = mvc.perform(get("/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		
		// then
		log.info(result.getRequest().getContentAsString());
	}
}
