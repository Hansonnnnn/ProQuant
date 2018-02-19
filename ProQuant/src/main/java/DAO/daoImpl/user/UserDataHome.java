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

import DAO.dao.UserDataDao;
import PO.user.UserData;

/**
 * Home object for domain model class UserData.
 * @see PO.user.UserData
 * @author Hibernate Tools
 */
@Repository("UserDataDao")
public class UserDataHome implements UserDataDao {

	private static final Log log = LogFactory.getLog(UserDataHome.class);

	@Autowired
	private SessionFactory sessionFactory;
	/**
		 (non-Javadoc)
	 * @see DAO.dao.UserDataDao#persist(PO.user.UserData)
	 */
	@Override
	public void persist(UserData transientInstance) {
		log.debug("persisting UserData instance");
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
	 * @see DAO.dao.UserDataDao#attachDirty(PO.user.UserData)
	 */
	@Override
	public void attachDirty(UserData instance) {
		log.debug("attaching dirty UserData instance");
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
	 * @see DAO.dao.UserDataDao#attachClean(PO.user.UserData)
	 */
	@Override
	public void attachClean(UserData instance) {
		log.debug("attaching clean UserData instance");
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
	 * @see DAO.dao.UserDataDao#delete(PO.user.UserData)
	 */
	@Override
	public void delete(UserData persistentInstance) {
		log.debug("deleting UserData instance");
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
	 * @see DAO.dao.UserDataDao#merge(PO.user.UserData)
	 */
	@Override
	public UserData merge(UserData detachedInstance) {
		log.debug("merging UserData instance");
		try {
			UserData result = (UserData) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
		 (non-Javadoc)
	 * @see DAO.dao.UserDataDao#findById(java.lang.String)
	 */
	@Override
	public UserData findById(java.lang.String id) {
		log.debug("getting UserData instance with id: " + id);
		try {
			UserData instance = (UserData) sessionFactory.getCurrentSession().get("PO.user.UserData", id);
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
	 * @see DAO.dao.UserDataDao#findByExample(PO.user.UserData)
	 */
	@Override
	public List findByExample(UserData instance) {
		log.debug("finding UserData instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("PO.user.UserData")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@Override
	public List<String> getAllUserName() {
		String hql = "select username from UserData";
		
		List<String> result = sessionFactory.getCurrentSession().createQuery(hql,String.class).getResultList();
		return result;
	}
}
