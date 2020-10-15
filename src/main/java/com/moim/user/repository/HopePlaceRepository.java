package com.moim.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moim.user.entity.HopePlace;
import com.moim.user.entity.User;

/**
 * HopePlace.java
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
public interface HopePlaceRepository extends JpaRepository<HopePlace, Long> {

	long countByUser(User user);
	List<HopePlace> findByUser(User user);
}
