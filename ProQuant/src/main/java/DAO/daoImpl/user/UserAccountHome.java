package DAO.daoImpl.user;
// Generated 2017-6-2 16:54:51 by Hibernate Tools 5.2.1.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import DAO.dao.UserAccountDao;
import PO.user.UserAccount;

/**
 * Home object for domain model class UserAccount.
 * @see PO.user.UserAccount
 * @author Hibernate Tools
 */
@Repository("UserAccountDao")
public class UserAccountHome implements UserAccountDao {

	private static final Log log = LogFactory.getLog(UserAccountHome.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
		 (non-Javadoc)
	 * @see DAO.dao.UserAccountDao#persist(PO.user.UserAccount)
	 */
	@Override
	public void persist(UserAccount transientInstance) {
		log.debug("persisting UserAccount instance");
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
	 * @see DAO.dao.UserAccountDao#attachDirty(PO.user.UserAccount)
	 */
	@Override
	public void attachDirty(UserAccount instance) {
		log.debug("attaching dirty UserAccount instance");
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
	 * @see DAO.dao.UserAccountDao#attachClean(PO.user.UserAccount)
	 */
	@Override
	public void attachClean(UserAccount instance) {
		log.debug("attaching clean UserAccount instance");
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
	 * @see DAO.dao.UserAccountDao#delete(PO.user.UserAccount)
	 */
	@Override
	public void delete(UserAccount persistentInstance) {
		log.debug("deleting UserAccount instance");
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
	 * @see DAO.dao.UserAccountDao#merge(PO.user.UserAccount)
	 */
	@Override
	public UserAccount merge(UserAccount detachedInstance) {
		log.debug("merging UserAccount instance");
		try {
			UserAccount result = (UserAccount) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
		 (non-Javadoc)
	 * @see DAO.dao.UserAccountDao#findById(java.lang.String)
	 */
	@Override
	public UserAccount findById(java.lang.String id) {
		log.debug("getting UserAccount instance with id: " + id);
		try {
			UserAccount instance = (UserAccount) sessionFactory.getCurrentSession().get("PO.user.UserAccount",
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
	 * @see DAO.dao.UserAccountDao#findByExample(PO.user.UserAccount)
	 */
	@Override
	public List findByExample(UserAccount instance) {
		log.debug("finding UserAccount instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("PO.user.UserAccount")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
