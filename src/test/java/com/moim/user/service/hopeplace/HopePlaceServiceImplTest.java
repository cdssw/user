package com.moim.user.service.hopeplace;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.moim.user.component.CommonComponent;
import com.moim.user.entity.HopePlace;
import com.moim.user.entity.User;
import com.moim.user.except.UserBusinessException;
import com.moim.user.repository.HopePlaceRepository;
import com.moim.user.repository.UserRepository;

/**
 * HopePlaceServiceImplTest.java
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
@RunWith(MockitoJUnitRunner.class)
public class HopePlaceServiceImplTest {
	
	// 테스트 하고자 하는 class
	private HopePlaceServiceImpl hopePlaceServiceImpl;
	
	@Mock private UserRepository userRepository;
	@Mock private HopePlaceRepository hopePlaceRepository;
	
	private HopePlaceDto.HopePlaceReq dto = null;
	private User user = null;

	@Before
	public void setUp() {
		CommonComponent commonComponent = new CommonComponent();
		hopePlaceServiceImpl = new HopePlaceServiceImpl(userRepository, hopePlaceRepository, commonComponent);
		dto = HopePlaceDto.HopePlaceReq.builder()
				.sido("경기도")
				.sgg("용인시 처인구")
				.build();
		user = User.builder()
				.username("cdssw@naver.com")
				.build();
	}

	@Test
	public void testCreateHopePlace() {
		// given
		HopePlace hopePlace = mock(HopePlace.class);
		given(userRepository.findByUsername(any())).willReturn(user);
		given(hopePlaceRepository.countByUser(any())).willReturn(2L);
		given(hopePlaceRepository.save(any(HopePlace.class))).willReturn(hopePlace);
		given(hopePlace.getId()).willReturn(1L);
		
		// when
		Long id = hopePlaceServiceImpl.createHopePlace(user.getUsername(), dto);
		
		// then
		assertEquals(id, 1L);
	}
	
	@Test(expected = UserBusinessException.class)
	public void testCreateHopePlaceExcept() {
		// given
		given(userRepository.findByUsername(any())).willReturn(user);
		given(hopePlaceRepository.countByUser(any())).willReturn(3L);
		
		// when
		hopePlaceServiceImpl.createHopePlace(user.getUsername(), dto);
	}	
	
	@Test
	public void testDeleteHopePlace() {
		// given
		User user = mock(User.class);
		HopePlace hopePlace = mock(HopePlace.class);
		given(userRepository.findByUsername(any())).willReturn(user);
		given(hopePlaceRepository.findById(anyLong())).willReturn(Optional.of(hopePlace));
		given(hopePlace.getUser()).willReturn(user);
		given(user.getId()).willReturn(1L);
		
		// when
		hopePlaceServiceImpl.deleteHopePlace(1L, user.getUsername());
		
		// then
		verify(hopePlaceRepository).delete(any());
	}	
	
}
