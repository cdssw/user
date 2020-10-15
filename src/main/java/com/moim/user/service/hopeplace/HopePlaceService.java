package com.moim.user.service.hopeplace;

/**
 * HopePlaceService.java
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
public interface HopePlaceService {

	Long createHopePlace(final String username, HopePlaceDto.HopePlaceReq dto);
	void deleteHopePlace(final Long id, final String username);
}
