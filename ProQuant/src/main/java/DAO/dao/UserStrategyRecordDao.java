package DAO.dao;

import java.util.List;

import PO.user.UserStrategyRecord;

public interface UserStrategyRecordDao {

	void persist(UserStrategyRecord transientInstance);

	void attachDirty(UserStrategyRecord instance);

	void attachClean(UserStrategyRecord instance);

	void delete(UserStrategyRecord persistentInstance);

	UserStrategyRecord merge(UserStrategyRecord detachedInstance);

	UserStrategyRecord findById(int id);

	List findByExample(UserStrategyRecord instance);

	List<UserStrategyRecord> findByUsername(String username);

}