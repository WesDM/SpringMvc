package com.wesdm.springmvc.spittr;

public interface SpitterRepository {

	void save(Spitter spitter);

	Spitter findByUsername(String username);

}
