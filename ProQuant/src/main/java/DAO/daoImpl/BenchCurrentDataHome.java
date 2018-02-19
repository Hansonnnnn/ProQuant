// default package
// Generated 2017-5-25 12:54:40 by Hibernate Tools 4.0.1.Final
package DAO.daoImpl;
import java.util.ArrayList;
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
import org.springframework.transaction.annotation.Transactional;

import DAO.dao.BenchCurrentDataDao;
import PO.BenchCurrentData;

/**
 * Home object for domain model class BenchCurrentData.
 * @see .BenchCurrentData
 * @author Hibernate Tools
 */
@Repository("BenchCurrentDataDao")
public class BenchCurrentDataHome implements BenchCurrentDataDao {

	private static final Log log = LogFactory.getLog(BenchCurrentDataHome.class);

	@Autowired
	private SessionFactory sessionFactory;


	/**
		 (non-Javadoc)
	 * @see DAO.dao.BenchCurrentDataDao#persist(PO.BenchCurrentData)
	 */

	public void persist(BenchCurrentData transientInstance) {
		log.debug("persisting BenchCurrentData instance");
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
	 * @see DAO.dao.BenchCurrentDataDao#attachDirty(PO.BenchCurrentData)
	 */

	public void attachDirty(BenchCurrentData instance) {
		log.debug("attaching dirty BenchCurrentData instance");
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
	 * @see DAO.dao.BenchCurrentDataDao#attachClean(PO.BenchCurrentData)
	 */

	public void attachClean(BenchCurrentData instance) {
		log.debug("attaching clean BenchCurrentData instance");
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
	 * @see DAO.dao.BenchCurrentDataDao#delete(PO.BenchCurrentData)
	 */

	public void delete(BenchCurrentData persistentInstance) {
		log.debug("deleting BenchCurrentData instance");
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
	 * @see DAO.dao.BenchCurrentDataDao#merge(PO.BenchCurrentData)
	 */

	public BenchCurrentData merge(BenchCurrentData detachedInstance) {
		log.debug("merging BenchCurrentData instance");
		try {
			BenchCurrentData result = (BenchCurrentData) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
		 (non-Javadoc)
	 * @see DAO.dao.BenchCurrentDataDao#findById(java.lang.Integer)
	 */

	public BenchCurrentData findById(java.lang.Integer id) {
		log.debug("getting BenchCurrentData instance with id: " + id);
		try {
			BenchCurrentData instance = (BenchCurrentData) sessionFactory.getCurrentSession().get("PO.BenchCurrentData",
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
	 * @see DAO.dao.BenchCurrentDataDao#findByExample(PO.BenchCurrentData)
	 */

	public List findByExample(BenchCurrentData instance) {
		log.debug("finding BenchCurrentData instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("PO.BenchCurrentData")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	@SuppressWarnings("unchecked")

	@Override
	public BenchCurrentData queryByHql(String code) {
		String hql = "from BenchCurrentData where code ='" +code+"'";
		ArrayList<BenchCurrentData> list= (ArrayList<BenchCurrentData>) sessionFactory.getCurrentSession().createQuery(hql).list();
		return list.get(0);
	}
}
