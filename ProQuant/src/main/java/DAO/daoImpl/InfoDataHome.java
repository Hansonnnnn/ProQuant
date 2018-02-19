package DAO.daoImpl;
// default package
// Generated 2017-5-25 12:54:40 by Hibernate Tools 4.0.1.Final

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import DAO.dao.InfoDataDao;
import PO.BenchCurrentData;
import PO.InfoData;
import model.StockPlate;

/**
 * Home object for domain model class InfoData.
 * @see .InfoData
 * @author Hibernate Tools
 */
@Repository("InfoDataDao")
public class InfoDataHome implements InfoDataDao {

	private static final Log log = LogFactory.getLog(InfoDataHome.class);


	@Autowired
	private SessionFactory sessionFactory;


	/**
		 (non-Javadoc)
	 * @see DAO.dao.InfoDataDao#persist(PO.InfoData)
	 */

	public void persist(InfoData transientInstance) {
		log.debug("persisting InfoData instance");
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
	 * @see DAO.dao.InfoDataDao#attachDirty(PO.InfoData)
	 */

	public void attachDirty(InfoData instance) {
		log.debug("attaching dirty InfoData instance");
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
	 * @see DAO.dao.InfoDataDao#attachClean(PO.InfoData)
	 */

	public void attachClean(InfoData instance) {
		log.debug("attaching clean InfoData instance");
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
	 * @see DAO.dao.InfoDataDao#delete(PO.InfoData)
	 */

	public void delete(InfoData persistentInstance) {
		log.debug("deleting InfoData instance");
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
	 * @see DAO.dao.InfoDataDao#merge(PO.InfoData)
	 */

	public InfoData merge(InfoData detachedInstance) {
		log.debug("merging InfoData instance");
		try {
			InfoData result = (InfoData) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
		 (non-Javadoc)
	 * @see DAO.dao.InfoDataDao#findById(long)
	 */

	public InfoData findById(long id) {
		log.debug("getting InfoData instance with id: " + id);
		try {
			InfoData instance = (InfoData) sessionFactory.getCurrentSession().get("PO.InfoData", id);
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
	 * @see DAO.dao.InfoDataDao#findByExample(PO.InfoData)
	 */

	public List findByExample(InfoData instance) {
		log.debug("finding InfoData instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("PO.InfoData").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	

	@Override
	public InfoData queryByHql(String code) {
		String hql = "from InfoData where code ='" +code+"'";
		ArrayList<InfoData> list= (ArrayList<InfoData>) sessionFactory.getCurrentSession().createQuery(hql).list();
		return list.get(0);
	}


	@Override
	public String getName(String code) {
		String hql = "select name from InfoData where code ='" +code+"'";
		List name = sessionFactory.getCurrentSession().createQuery(hql).list();
		return (String) name.get(0);

	}

	@Override
	public Map<String,String> getCodeList() {
		String hql = "select name,code from InfoData";
		Map<String , String> result = new LinkedHashMap<>();
		List<Object[]> codes ;
	
		Session session = sessionFactory.openSession();
		codes = session.createQuery(hql).list();
		session.close();
		
		for (Object[] objects : codes) {
			result.put((String)objects[0],(String)objects[1]);
			
		}
		return result;
	}

	@Override
	public ArrayList<String> getStockListOfBench(StockPlate plate) {
		
		String hql = null;
		if (plate==null) {
			hql = "select code from InfoData";
		}
		else{
			switch (plate) {
			case CSI300:
				hql = "select code from InfoData where board = 3";
				break;
			case SME:
				hql = "select code from InfoData where code like '002%'";
				break;
				
			case CHINEXT:
				hql = "select code from InfoData where code like '300%'";
				break;
			default:
				hql = "select code from InfoData";
				break;
			}
		}
		
		
		ArrayList<String> list= (ArrayList<String>) sessionFactory.getCurrentSession().createQuery(hql).list();
		
		
		 
		return list;
	}
}
