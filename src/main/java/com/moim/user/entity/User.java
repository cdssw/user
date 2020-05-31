package com.moim.user.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * User.java
 * 
 * @author cdssw
 * @since Apr 12, 2020
 * @description 
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 12, 2020   cdssw            최초 생성
 * </pre>
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 20, unique = true, nullable = false)
    private String username;
	
    @Column(length = 400, nullable = false)
    private String password;
    
    @Column(nullable = false)
    private int userType;

	private String userNm;
	
	@Embedded
	private Address address;
	
	private String phone;
	
	@Builder
	public User(String username, String password, int userType, String userNm, Address address, String phone) {
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.userNm = userNm;
		this.address = address;
		this.phone = phone;
	}
}
