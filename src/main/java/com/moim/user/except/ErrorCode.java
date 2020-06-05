package com.moim.user.except;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ErrorCode.java
 * 
 * @author cdssw
 * @since Apr 18, 2020
 * @description 사용자 정의 ErrorCode
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 18, 2020   cdssw            최초 생성
 * </pre>
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

	INPUT_VALUE_INVALID("V_00001", "입력값이 올바르지 않습니다."),
	USER_NOT_FOUND("U_00001", "사용자가 존재하지 않습니다."),
	USER_INVALID("U_00002", "본인이 아닙니다."),
	ELEMENT_NOT_FOUND("E_00001", "항목이 존재하지 않습니다."),
	;
	
	private final String code;
	private final String message;
}
