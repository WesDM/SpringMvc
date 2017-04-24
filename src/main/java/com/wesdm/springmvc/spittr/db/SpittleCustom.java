package com.wesdm.springmvc.spittr.db;

import java.util.List;

import com.wesdm.springmvc.spittr.Spittle;

/**
 * Adding funtionality to jpa repository
 * @author Wesley
 *
 */
public interface SpittleCustom {
	List<Spittle> findSpittles(long max, int count);
}
