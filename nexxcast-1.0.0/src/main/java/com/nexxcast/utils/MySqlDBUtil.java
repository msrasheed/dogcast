package com.nexxcast.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MySqlDBUtil {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("primary");
	
	public static EntityManagerFactory getEmFactory() {
		return emf;
	}
	
	public static boolean insert(Object obj) {
		boolean isSuccessful = false;
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(obj);
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
	
	public static boolean merge(Object obj) {
		boolean isSuccessful = false;
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(obj);
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
	
	public static boolean drop(Object obj) {
		boolean isSuccessful = false;
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.remove(em.merge(obj));
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
