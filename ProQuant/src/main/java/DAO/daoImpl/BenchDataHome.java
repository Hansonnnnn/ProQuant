package DAO.daoImpl;
// Generated 2017-5-28 13:12:39 by Hibernate Tools 5.2.1.Final

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import DAO.dao.BenchDataDao;
import PO.BenchData;
import PO.BenchDataId;
import utility.DateFormater;

/**
 * Home object for domain model class BenchData.
 * @see .BenchData
 * @author Hibernate Tools
 */
@Repository("BenchDataDao")
public class BenchDataHome implements BenchDataDao{

	private static final Log log = LogFactory.getLog(BenchDataHome.class);
	@Autowired
	private SessionFactory sessionFactory;



	public void persist(BenchData transientInstance) {
		log.debug("persisting BenchData instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(BenchData instance) {
		log.debug("attaching dirty BenchData instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BenchData instance) {
		log.debug("attaching clean BenchData instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(BenchData persistentInstance) {
		log.debug("deleting BenchData instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BenchData merge(BenchData detachedInstance) {
		log.debug("merging BenchData instance");
		try {
			BenchData result = (BenchData) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public BenchData findById(BenchDataId id) {
		log.debug("getting BenchData instance with id: " + id);
		try {
			BenchData instance = (BenchData) sessionFactory.getCurrentSession().get("PO.BenchData", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BenchData instance) {
		log.debug("finding BenchData instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("PO.BenchData")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@Override
	public Map<Date, BenchData> queryByHql(String code,Date start,Date end) {
		StringBuilder hql = new StringBuilder("from BenchData where code ='").append(code).append("'");
		if (start!=null) {
			String s = DateFormater.formatDay(start);
			hql.append(" and date >='").append(s).append("'");
		}
		if (end!=null) {
			String e = DateFormater.formatDay(end);
			hql.append(" and date <='").append(e).append("'");
		}
		hql.append(" order by date");
		ArrayList<BenchData> list= (ArrayList<BenchData>) sessionFactory.getCurrentSession().createQuery(hql.toString()).list();
		Map<Date, BenchData> result = new LinkedHashMap<>();
		for (BenchData benchData : list) {
			result.put(benchData.getId().getDate(), benchData);
		}
		
		return result;
	}

	@Override
	public Map<Date, BenchData> queryByHql(Date end, int count, String code) {
		StringBuilder hql = new StringBuilder("from BenchData where code ='").append(code).append("'");
		if (end!=null) {
			String e = DateFormater.formatDay(end);
			hql.append(" and date <'").append(e).append("'");
		}
		hql.append(" order by date desc");

		ArrayList<BenchData> list= (ArrayList<BenchData>) sessionFactory.getCurrentSession().createQuery(hql.toString()).setMaxResults(count).list();
		Map<Date, BenchData> result = new LinkedHashMap<>();
		for (BenchData BenchData : list) {
			result.put(BenchData.getId().getDate(), BenchData);
		}
		
		return result;
		
		 
	}

}
