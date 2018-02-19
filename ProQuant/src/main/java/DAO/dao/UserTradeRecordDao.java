package DAO.dao;

import java.util.List;

import PO.user.UserTradeRecord;

public interface UserTradeRecordDao {

	void persist(UserTradeRecord transientInstance);

	void attachDirty(UserTradeRecord instance);

	void attachClean(UserTradeRecord instance);

	void delete(UserTradeRecord persistentInstance);

	UserTradeRecord merge(UserTradeRecord detachedInstance);

	UserTradeRecord findById(java.lang.String id);

	List findByExample(UserTradeRecord instance);

	List<UserTradeRecord> getUserTradeRecord(String username);

}