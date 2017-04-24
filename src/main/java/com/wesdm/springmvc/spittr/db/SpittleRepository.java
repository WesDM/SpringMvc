package com.wesdm.springmvc.spittr.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wesdm.springmvc.spittr.DuplicateSpittleException;
import com.wesdm.springmvc.spittr.Spittle;

public interface SpittleRepository extends JpaRepository<Spittle, Long>, SpittleCustom{
//	
//	void save(Spittle spittle) throws DuplicateSpittleException;
//	
//	List<Spittle> findSpittles(long max, int count);
//
//	Spittle findOne(long spittleId);
}
