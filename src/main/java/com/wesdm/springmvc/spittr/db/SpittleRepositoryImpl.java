package com.wesdm.springmvc.spittr.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.wesdm.springmvc.spittr.DuplicateSpittleException;
import com.wesdm.springmvc.spittr.Spittle;

/**
 * Add custom methods to Spittle Repository interface
 * @author Wesley
 *
 */
@Repository
public class SpittleRepositoryImpl implements SpittleCustom {

	@Override
	public List<Spittle> findSpittles(long max, int count) {
		// TODO Auto-generated method stub
		return null;
	}



//	@PersistenceContext
//	private EntityManager em;
//
//	@Override
//	public List<Spittle> findSpittles(long max, int count) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Spittle findOne(long spittleId) {
//		return em.find(Spittle.class, spittleId);
//	}
//
//	@Override
//	public void save(Spittle spittle) throws DuplicateSpittleException {
//		em.persist(spittle);
//	}

}
