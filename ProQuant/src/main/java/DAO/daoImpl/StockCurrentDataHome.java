package DAO.daoImpl;
// default package
// Generated 2017-5-25 12:54:40 by Hibernate Tools 4.0.1.Final

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import DAO.dao.StockCurrentDataDao;
import PO.StockCurrentData;

/**
 * Home object for domain model class StockCurrentData.
 * @see .StockCurrentData
 * @author Hibernate Tools
 */
@Repository("StockCurrentDataDao")
public class StockCurrentDataHome implements StockCurrentDataDao {

	private static final Log log = LogFactory.getLog(StockCurrentDataHome.class);


	@Autowired
	private SessionFactory sessionFactory;


	/**
		 (non-Javadoc)
	 * @see DAO.dao.StockCurrentDataDao#persist(PO.StockCurrentData)
	 */

	public void persist(StockCurrentData transientInstance) {
		log.debug("persisting StockCurrentData instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
		 (non-Javadoc)
	 * @see DAO.dao.StockCurrentDataDao#attachDirty(PO.StockCurrentData)
	 */

	public void attachDirty(StockCurrentData instance) {
		log.debug("attaching dirty StockCurrentData instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
		 (non-Javadoc)
	 * @see DAO.dao.StockCurrentDataDao#attachClean(PO.StockCurrentData)
	 */

	public void attachClean(StockCurrentData instance) {
		log.debug("attaching clean StockCurrentData instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
		 (non-Javadoc)
	 * @see DAO.dao.StockCurrentDataDao#delete(PO.StockCurrentData)
	 */

	public void delete(StockCurrentData persistentInstance) {
		log.debug("deleting StockCurrentData instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/**
		 (non-Javadoc)
	 * @see DAO.dao.StockCurrentDataDao#merge(PO.StockCurrentData)
	 */

	public StockCurrentData merge(StockCurrentData detachedInstance) {
		log.debug("merging StockCurrentData instance");
		try {
			StockCurrentData result = (StockCurrentData) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
		 (non-Javadoc)
	 * @see DAO.dao.StockCurrentDataDao#findById(PO.StockCurrentDataId)
	 */

	public StockCurrentData findById(long id) {
		log.debug("getting StockCurrentData instance with id: " + id);
		try {
			StockCurrentData instance = (StockCurrentData) sessionFactory.getCurrentSession().get("PO.StockCurrentData",
					id);
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


	/**
		 (non-Javadoc)
	 * @see DAO.dao.StockCurrentDataDao#findByExample(PO.StockCurrentData)
	 */

	public List findByExample(StockCurrentData instance) {
		log.debug("finding StockCurrentData instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("PO.StockCurrentData")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}


	@Override
	public StockCurrentData queryByHql(String code) {
		String hql = "from StockCurrentData where code ='" +code+"'";
		ArrayList<StockCurrentData> list= (ArrayList<StockCurrentData>) sessionFactory.getCurrentSession().createQuery(hql).list();
		return list.get(0);
		
	}
}
