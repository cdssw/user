package com.moim.kafka;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * EventUser.java
 * 
 * @author cdssw
 * @since 2020. 6. 5.
 * @description  kafka 이벤트 객체, 수신쪽과 같은 패키지명으로 등록되어야 함
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 2020. 6. 5.    cdssw            최초 생성
 * </pre>
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class EventUser {

	private Long id;
	private String username;
	private String userNm;
	private String phone;
}
