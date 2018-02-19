package DAO.daoImpl;
// Generated 2017-5-28 13:18:58 by Hibernate Tools 5.2.1.Final

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import DAO.dao.StockDataDao;
import PO.StockData;
import PO.StockData;
import PO.StockDataId;
import model.StockPlate;
import utility.DateFormater;

/**
 * Home object for domain model class StockData.
 * @see DAO.StockData
 * @author Hibernate Tools
 */
@Repository("StockDataDao")
public class StockDataHome implements StockDataDao{

	private static final Log log = LogFactory.getLog(StockDataHome.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void persist(StockData transientInstance) {
		log.debug("persisting StockData instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(StockData instance) {
		log.debug("attaching dirty StockData instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StockData instance) {
		log.debug("attaching clean StockData instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(StockData persistentInstance) {
		log.debug("deleting StockData instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StockData merge(StockData detachedInstance) {
		log.debug("merging StockData instance");
		try {
			StockData result = (StockData) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public StockData findById(StockDataId id) {
		log.debug("getting StockData instance with id: " + id);
		try {
			StockData instance = (StockData) sessionFactory.getCurrentSession().get("PO.StockData", id);
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

	public List findByExample(StockData instance) {
		log.debug("finding StockData instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("PO.StockData")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@Override
	public Map<Date, StockData> queryByHql(String code,Date start,Date end) {
		StringBuilder hql = new StringBuilder("from StockData where code ='").append(code).append("'");
		if (start!=null) {
			String s = DateFormater.formatDay(start);
			hql.append(" and date >='").append(s).append("'");
		}
		if (end!=null) {
			String e = DateFormater.formatDay(end);
			hql.append(" and date <='").append(e).append("'");
		}
		hql.append(" order by date ");
		ArrayList<StockData> list= (ArrayList<StockData>) sessionFactory.getCurrentSession().createQuery(hql.toString()).list();
		Map<Date, StockData> result = new LinkedHashMap<>();
		for (StockData StockData : list) {
			result.put(StockData.getId().getDate(), StockData);
		}
		
		return result;
	}

	@Override
	public Map<Date,StockData> queryByHql(String code,int count,Date end){
		StringBuilder hql = new StringBuilder("from StockData where code ='").append(code).append("'");
		if (end!=null) {
			String e = DateFormater.formatDay(end);
			hql.append(" and date <='").append(e).append("'");
		}
		hql.append(" order by date desc");

		ArrayList<StockData> list= (ArrayList<StockData>) sessionFactory.getCurrentSession().createQuery(hql.toString()).setMaxResults(count).list();
		Map<Date, StockData> result = new LinkedHashMap<>();
		for (StockData StockData : list) {
			result.put(StockData.getId().getDate(), StockData);
		}
		
		return result;
	}

	@Override
	public List<StockData> queryBuHql(StockPlate plate, Date start, Date end) {
		StringBuilder hql = new StringBuilder();
		switch (plate) {
		case CSI300:
			hql.append("from StockData where code in (select code from Hs300sData) ");
			break;
		case SME:
			hql.append("from StockData where code like'002%' ");
			break;
		case CHINEXT:
			hql.append("from StockData where code like'300%' ");
			break;
		default:
			return null;
		}
		
		 
		return sessionFactory.getCurrentSession().createQuery(hql.toString()).list();
	}
}
