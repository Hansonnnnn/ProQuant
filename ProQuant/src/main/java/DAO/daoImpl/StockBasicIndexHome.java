package DAO.daoImpl;
// Generated 2017-6-10 0:50:47 by Hibernate Tools 5.2.1.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import DAO.dao.StockBasicIndexDao;
import PO.StockBasicIndex;

/**
 * Home object for domain model class StockBasicIndex.
 * @see PO.StockBasicIndex
 * @author Hibernate Tools
 */
@Repository("StockBasicIndexDao")
public class StockBasicIndexHome implements StockBasicIndexDao {

	private static final Log log = LogFactory.getLog(StockBasicIndexHome.class);

	@Autowired
	private SessionFactory sessionFactory;


	/**
		 (non-Javadoc)
	 * @see DAO.dao.StockBasicIndexDao#persist(PO.StockBasicIndex)
	 */
	@Override
	public void persist(StockBasicIndex transientInstance) {
		log.debug("persisting StockBasicIndex instance");
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
	 * @see DAO.dao.StockBasicIndexDao#attachDirty(PO.StockBasicIndex)
	 */
	@Override
	public void attachDirty(StockBasicIndex instance) {
		log.debug("attaching dirty StockBasicIndex instance");
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
	 * @see DAO.dao.StockBasicIndexDao#attachClean(PO.StockBasicIndex)
	 */
	@Override
	public void attachClean(StockBasicIndex instance) {
		log.debug("attaching clean StockBasicIndex instance");
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
	 * @see DAO.dao.StockBasicIndexDao#delete(PO.StockBasicIndex)
	 */
	@Override
	public void delete(StockBasicIndex persistentInstance) {
		log.debug("deleting StockBasicIndex instance");
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
	 * @see DAO.dao.StockBasicIndexDao#merge(PO.StockBasicIndex)
	 */
	@Override
	public StockBasicIndex merge(StockBasicIndex detachedInstance) {
		log.debug("merging StockBasicIndex instance");
		try {
			StockBasicIndex result = (StockBasicIndex) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
		 (non-Javadoc)
	 * @see DAO.dao.StockBasicIndexDao#findById(java.lang.String)
	 */
	@Override
	public StockBasicIndex findById(java.lang.String id) {
		log.debug("getting StockBasicIndex instance with id: " + id);
		try {
			StockBasicIndex instance = (StockBasicIndex) sessionFactory.getCurrentSession()
					.get("PO.StockBasicIndex", id);
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
	 * @see DAO.dao.StockBasicIndexDao#findByExample(PO.StockBasicIndex)
	 */
	@Override
	public List findByExample(StockBasicIndex instance) {
		log.debug("finding StockBasicIndex instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("PO.StockBasicIndex")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
