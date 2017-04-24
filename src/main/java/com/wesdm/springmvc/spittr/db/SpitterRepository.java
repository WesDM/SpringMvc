package com.wesdm.springmvc.spittr.db;

import com.wesdm.springmvc.spittr.Spitter;

public interface SpitterRepository {

	void save(Spitter spitter);

	Spitter findByUsername(String username);

}
