package com.moim.user.service.hopeplace;

import javax.validation.constraints.NotBlank;

import com.moim.user.entity.HopePlace;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * HopePlaceDto.java
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
public class HopePlaceDto {

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class HopePlaceReq {
		
		@NotBlank
		private String sido;
		
		@NotBlank
		private String sgg;
		
		@Builder
		public HopePlaceReq(String sido, String sgg) {
			this.sido = sido;
			this.sgg = sgg;
		}
		
		public HopePlace toEntity() {
			return HopePlace.builder()
					.sido(sido)
					.sgg(sgg)
					.build();
		}

	}
	

}
