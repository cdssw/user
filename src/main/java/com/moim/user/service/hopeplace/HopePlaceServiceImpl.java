package com.moim.user.service.hopeplace;

import org.springframework.stereotype.Service;

import com.moim.user.component.CommonComponent;
import com.moim.user.entity.HopePlace;
import com.moim.user.entity.User;
import com.moim.user.except.ErrorCode;
import com.moim.user.except.UserBusinessException;
import com.moim.user.repository.HopePlaceRepository;
import com.moim.user.repository.UserRepository;
import com.moim.user.service.hopeplace.HopePlaceDto.HopePlaceReq;

import lombok.AllArgsConstructor;

/**
 * HopePlaceServiceImpl.java
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
@Service
public class HopePlaceServiceImpl implements HopePlaceService {

	private UserRepository userRepository;
	private HopePlaceRepository hopePlaceRepository;
	private CommonComponent commonComponent;
	
	@Override
	public Long createHopePlace(String username, HopePlaceReq dto) {
		User user = userRepository.findByUsername(username);
		long cnt = hopePlaceRepository.countByUser(user);
		if(cnt >= 3) {
			throw new UserBusinessException(ErrorCode.MAXIMUM_COUNT);
		}
		
		HopePlace hopePlace = dto.toEntity();
		hopePlace.editUser(user);
		return hopePlaceRepository.save(hopePlace).getId();
	}

	@Override
	public void deleteHopePlace(Long id, String username) {
		User user = userRepository.findByUsername(username);
		HopePlace hopePlace = commonComponent.findById(hopePlaceRepository, id, HopePlace.class);
		if(hopePlace.getUser().getId() != user.getId()) {
			throw new UserBusinessException(ErrorCode.USER_INVALID);
		}
		
		hopePlaceRepository.delete(hopePlace);
	}

}
