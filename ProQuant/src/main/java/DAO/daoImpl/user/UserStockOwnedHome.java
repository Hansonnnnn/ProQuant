package DAO.daoImpl.user;
// Generated 2017-6-2 16:54:51 by Hibernate Tools 5.2.1.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import DAO.dao.UserStockOwnedDao;
import PO.user.UserStockOwned;

/**
 * Home object for domain model class UserStockOwned.
 * @see PO.user.UserStockOwned
 * @author Hibernate Tools
 */
@Repository("UserStockOwnedDao")
public class UserStockOwnedHome implements UserStockOwnedDao {

	private static final Log log = LogFactory.getLog(UserStockOwnedHome.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
		 (non-Javadoc)
	 * @see DAO.dao.UserStockOwnedDao#persist(PO.user.UserStockOwned)
	 */
	@Override
	public void persist(UserStockOwned transientInstance) {
		log.debug("persisting UserStockOwned instance");
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
	 * @see DAO.dao.UserStockOwnedDao#attachDirty(PO.user.UserStockOwned)
	 */
	@Override
	public void attachDirty(UserStockOwned instance) {
		log.debug("attaching dirty UserStockOwned instance");
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
	 * @see DAO.dao.UserStockOwnedDao#attachClean(PO.user.UserStockOwned)
	 */
	@Override
	public void attachClean(UserStockOwned instance) {
		log.debug("attaching clean UserStockOwned instance");
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
	 * @see DAO.dao.UserStockOwnedDao#delete(PO.user.UserStockOwned)
	 */
	@Override
	public void delete(UserStockOwned persistentInstance) {
		log.debug("deleting UserStockOwned instance");
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
	 * @see DAO.dao.UserStockOwnedDao#merge(PO.user.UserStockOwned)
	 */
	@Override
	public UserStockOwned merge(UserStockOwned detachedInstance) {
		log.debug("merging UserStockOwned instance");
		try {
			UserStockOwned result = (UserStockOwned) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
		 (non-Javadoc)
	 * @see DAO.dao.UserStockOwnedDao#findById(PO.user.UserStockOwnedId)
	 */
	@Override
	public UserStockOwned findById(PO.user.UserStockOwnedId id) {
		log.debug("getting UserStockOwned instance with id: " + id);
		try {
			UserStockOwned instance = (UserStockOwned) sessionFactory.getCurrentSession()
					.get("PO.user.UserStockOwned", id);
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
	 * @see DAO.dao.UserStockOwnedDao#findByExample(PO.user.UserStockOwned)
	 */
	@Override
	public List findByExample(UserStockOwned instance) {
		log.debug("finding UserStockOwned instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("PO.user.UserStockOwned")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@Override
	public List<UserStockOwned> getUserStockOwned(String username) {
		String hql = "from UserStockOwned where username = '"+username+"'";
		List<UserStockOwned> result = sessionFactory.getCurrentSession().createQuery(hql, UserStockOwned.class).list();
		
		return result;
	}
	
	
}
