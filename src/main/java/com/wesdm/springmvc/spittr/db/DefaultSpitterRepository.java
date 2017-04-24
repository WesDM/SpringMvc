package com.wesdm.springmvc.spittr.db;

import org.springframework.stereotype.Repository;

import com.wesdm.springmvc.spittr.Spitter;

@Repository
public class DefaultSpitterRepository implements SpitterRepository {

	@Override
	public void save(Spitter spitter) {
		// TODO Auto-generated method stub

	}

	@Override
	public Spitter findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
