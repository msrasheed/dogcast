package com.nexxcast.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.nexxcast.model.PartyQueue;

public class PartyQueueDB {
	public static PartyQueue getQueue(String code) {
		EntityManager em = MySqlDBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT pq " + 
						 "FROM PartyQueue as pq " + 
						 "WHERE pq.code = :code";
		TypedQuery<PartyQueue> q = em.createQuery(qString, PartyQueue.class);
		q.setParameter("code", code);
		
		try {
			return q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally { 
			em.close();
		}
	}
	
	public static boolean insertQueue(PartyQueue queue) {
		boolean isSuccessful = false;
		EntityManager em = MySqlDBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(queue);
			trans.commit();
			isSuccessful = true;
		} catch (Exception e) {
			trans.rollback();
			isSuccessful = false;
		} finally {
			em.close();
		}
		return isSuccessful;
	}
	
	public static boolean dropQueue(PartyQueue queue) {
		boolean isSuccessful = false;
		EntityManager em = MySqlDBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.remove(em.merge(queue));
			trans.commit();
			isSuccessful = true;
		} catch (Exception e) {
			trans.rollback();
			isSuccessful = false;
		} finally {
			em.close();
		}
		return isSuccessful;
	}
}
