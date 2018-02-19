package DAO.daoImpl;
// default package
// Generated 2017-5-25 12:54:40 by Hibernate Tools 4.0.1.Final

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import DAO.dao.Hs300sDataDao;
import PO.Hs300sData;

/**
 * Home object for domain model class Hs300sData.
 * @see .Hs300sData
 * @author Hibernate Tools
 */
@Repository("Hs300sDataDao")
public class Hs300sDataHome implements Hs300sDataDao {

	private static final Log log = LogFactory.getLog(Hs300sDataHome.class);


	@Autowired
	private SessionFactory sessionFactory;


	/**
		 (non-Javadoc)
	 * @see DAO.dao.Hs300sDataDao#persist(PO.Hs300sData)
	 */

	public void persist(Hs300sData transientInstance) {
		log.debug("persisting Hs300sData instance");
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
	 * @see DAO.dao.Hs300sDataDao#attachDirty(PO.Hs300sData)
	 */

	public void attachDirty(Hs300sData instance) {
		log.debug("attaching dirty Hs300sData instance");
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
	 * @see DAO.dao.Hs300sDataDao#attachClean(PO.Hs300sData)
	 */

	public void attachClean(Hs300sData instance) {
		log.debug("attaching clean Hs300sData instance");
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
	 * @see DAO.dao.Hs300sDataDao#delete(PO.Hs300sData)
	 */

	public void delete(Hs300sData persistentInstance) {
		log.debug("deleting Hs300sData instance");
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
	 * @see DAO.dao.Hs300sDataDao#merge(PO.Hs300sData)
	 */

	public Hs300sData merge(Hs300sData detachedInstance) {
		log.debug("merging Hs300sData instance");
		try {
			Hs300sData result = (Hs300sData) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
		 (non-Javadoc)
	 * @see DAO.dao.Hs300sDataDao#findById(long)
	 */

	public Hs300sData findById(long id) {
		log.debug("getting Hs300sData instance with id: " + id);
		try {
			Hs300sData instance = (Hs300sData) sessionFactory.getCurrentSession().get("PO.Hs300sData", id);
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
	 * @see DAO.dao.Hs300sDataDao#findByExample(PO.Hs300sData)
	 */

	public List findByExample(Hs300sData instance) {
		log.debug("finding Hs300sData instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("PO.Hs300sData").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
