package com.wesdm.springmvc.spittr.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wesdm.springmvc.spittr.Spittle;

public interface SpittleRepository extends JpaRepository<Spittle, Long>, SpittleCustom{

	
	
	@Query("select s from Spittle s where s.message like 'Zing%'")	//@Query gives jpa hint on how to generate query. limited to single query
	List<Spittle> findAllSpittlesStartsWithZing();
//	
//	void save(Spittle spittle) throws DuplicateSpittleException;
//	
//	List<Spittle> findSpittles(long max, int count);
//
//	Spittle findOne(long spittleId);
}
