package DAO.daoImpl.user;
// Generated 2017-6-6 1:38:42 by Hibernate Tools 4.0.1.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import DAO.dao.UserStockPoolDao;
import PO.user.UserStockPool;

/**
 * Home object for domain model class UserStockPool.
 * @see PO.user.UserStockPool
 * @author Hibernate Tools
 */
@Repository("UserStockPoolDao")
public class UserStockPoolHome implements UserStockPoolDao {

	private static final Log log = LogFactory.getLog(UserStockPoolHome.class);


	@Autowired
	private SessionFactory sessionFactory;


	/**
		 (non-Javadoc)
	 * @see DAO.dao.UserStockPoolDao#persist(PO.user.UserStockPool)
	 */
	@Override
	public void persist(UserStockPool transientInstance) {
		log.debug("persisting UserStockPool instance");
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
	 * @see DAO.dao.UserStockPoolDao#attachDirty(PO.user.UserStockPool)
	 */
	@Override
	public void attachDirty(UserStockPool instance) {
		log.debug("attaching dirty UserStockPool instance");
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
	 * @see DAO.dao.UserStockPoolDao#attachClean(PO.user.UserStockPool)
	 */
	@Override
	public void attachClean(UserStockPool instance) {
		log.debug("attaching clean UserStockPool instance");
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
	 * @see DAO.dao.UserStockPoolDao#delete(PO.user.UserStockPool)
	 */
	@Override
	public boolean delete(UserStockPool persistentInstance) {
		log.debug("deleting UserStockPool instance");
		String hql = "delete UserStockPool where username = '"+persistentInstance.getUsername()+"' and stockcode = '"+persistentInstance.getStockcode()+"'";
		try {
			int n = sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
			log.debug("delete successful");
			return n==0?false:true;
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/**
		 (non-Javadoc)
	 * @see DAO.dao.UserStockPoolDao#merge(PO.user.UserStockPool)
	 */
	@Override
	public UserStockPool merge(UserStockPool detachedInstance) {
		log.debug("merging UserStockPool instance");
		try {
			UserStockPool result = (UserStockPool) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
		 (non-Javadoc)
	 * @see DAO.dao.UserStockPoolDao#findById(int)
	 */
	@Override
	public UserStockPool findById(int id) {
		log.debug("getting UserStockPool instance with id: " + id);
		try {
			UserStockPool instance = (UserStockPool) sessionFactory.getCurrentSession().get("PO.user.UserStockPool",
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
	 * @see DAO.dao.UserStockPoolDao#findByExample(PO.user.UserStockPool)
	 */
	@Override
	public List findByExample(UserStockPool instance) {
		log.debug("finding UserStockPool instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("PO.user.UserStockPool")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@Override
	public List<String> findByUsername(String username) {
		String hql = "select stockcode from UserStockPool where username ='"+username+"'";
		List<String> result =  sessionFactory.getCurrentSession().createQuery(hql, String.class).getResultList();
		
		return result;
	}

	@Override
	public boolean hasExist(String username, String stockcode) {
		String hql = "from UserStockPool where username ='"+username+"' and stockcode = '"+stockcode+"'";
		List<UserStockPool> result =  sessionFactory.getCurrentSession().createQuery(hql, UserStockPool.class).getResultList();
		if (result.size()==0) {
			return false;
		}
		return true;
	}
}
