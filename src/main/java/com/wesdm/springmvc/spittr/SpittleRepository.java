package com.wesdm.springmvc.spittr;

import java.util.List;

public interface SpittleRepository {
	
	void save(Spittle spittle) throws DuplicateSpittleException;
	
	List<Spittle> findSpittles(long max, int count);

	Spittle findOne(long spittleId);
}
