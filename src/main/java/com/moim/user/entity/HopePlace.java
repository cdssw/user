package com.moim.user.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * HopePlace.java
 * 
 * @author cdssw
 * @since 2020. 9. 1.
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 2020. 9. 1.    cdssw            최초 생성
 * </pre>
 */
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HopePlace {

	@NotBlank
	private String place1;
	
	@NotBlank
	private String place2;
	
	@NotBlank
	private String place3;
	
	@Builder
	public HopePlace(String place1, String place2, String place3) {
		this.place1 = place1;
		this.place2 = place2;
		this.place3 = place3;
	}
}
