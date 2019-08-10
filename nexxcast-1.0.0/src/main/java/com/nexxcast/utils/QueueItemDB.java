package com.nexxcast.utils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.nexxcast.model.QueueItem;

public class QueueItemDB {
	public static QueueItem getQueueItem(long id) {
		EntityManager em = MySqlDBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT qi " + 
						 "FROM QueueItem as qi " + 
						 "WHERE qi.id = :id";
		TypedQuery<QueueItem> q = em.createQuery(qString, QueueItem.class);
		q.setParameter("id", id);
		
		try {
			return q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally { 
			em.close();
		}
	}
}
