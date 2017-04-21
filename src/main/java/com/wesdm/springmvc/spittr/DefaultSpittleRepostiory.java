package com.wesdm.springmvc.spittr;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class DefaultSpittleRepostiory implements SpittleRepository {

	@Override
	public List<Spittle> findSpittles(long max, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Spittle findOne(long spittleId) {
		// TODO Auto-generated method stub
		return null;
	}

}
