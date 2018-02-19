package DAO.daoImpl.user;
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

import DAO.dao.UserStrategyRecordDao;
import PO.user.UserStockPool;
import PO.user.UserStrategyRecord;

/**
 * Home object for domain model class UserStrategyRecord.
 * @see PO.user.UserStrategyRecord
 * @author Hibernate Tools
 */
@Repository("UserStrategyRecordDao")
public class UserStrategyRecordHome implements UserStrategyRecordDao {

	private static final Log log = LogFactory.getLog(UserStrategyRecordHome.class);

	@Autowired
	private SessionFactory sessionFactory;


	/**
		 (non-Javadoc)
	 * @see DAO.dao.UserStrategyRecordDao#persist(PO.user.UserStrategyRecord)
	 */
	@Override
	public void persist(UserStrategyRecord transientInstance) {
		log.debug("persisting UserStrategyRecord instance");
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
	 * @see DAO.dao.UserStrategyRecordDao#attachDirty(PO.user.UserStrategyRecord)
	 */
	@Override
	public void attachDirty(UserStrategyRecord instance) {
		log.debug("attaching dirty UserStrategyRecord instance");
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
	 * @see DAO.dao.UserStrategyRecordDao#attachClean(PO.user.UserStrategyRecord)
	 */
	@Override
	public void attachClean(UserStrategyRecord instance) {
		log.debug("attaching clean UserStrategyRecord instance");
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
	 * @see DAO.dao.UserStrategyRecordDao#delete(PO.user.UserStrategyRecord)
	 */
	@Override
	public void delete(UserStrategyRecord persistentInstance) {
		log.debug("deleting UserStrategyRecord instance");
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
	 * @see DAO.dao.UserStrategyRecordDao#merge(PO.user.UserStrategyRecord)
	 */
	@Override
	public UserStrategyRecord merge(UserStrategyRecord detachedInstance) {
		log.debug("merging UserStrategyRecord instance");
		try {
			UserStrategyRecord result = (UserStrategyRecord) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
		 (non-Javadoc)
	 * @see DAO.dao.UserStrategyRecordDao#findById(int)
	 */
	@Override
	public UserStrategyRecord findById(int id) {
		log.debug("getting UserStrategyRecord instance with id: " + id);
		try {
			UserStrategyRecord instance = (UserStrategyRecord) sessionFactory.getCurrentSession()
					.get("PO.user.UserStrategyRecord", id);
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
	 * @see DAO.dao.UserStrategyRecordDao#findByExample(PO.user.UserStrategyRecord)
	 */
	@Override
	public List findByExample(UserStrategyRecord instance) {
		log.debug("finding UserStrategyRecord instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("PO.user.UserStrategyRecord")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	@Override
	public List<UserStrategyRecord> findByUsername(String username){
		String hql = "from UserStrategyRecord where username = '" +username +"'";
		List<UserStrategyRecord> result = sessionFactory.getCurrentSession().createQuery(hql, UserStrategyRecord.class).getResultList();
		return result;
	}
}
