package DAO.dao;

import java.util.List;

import PO.user.UserStockPool;

public interface UserStockPoolDao {

	void persist(UserStockPool transientInstance);

	void attachDirty(UserStockPool instance);

	void attachClean(UserStockPool instance);

	boolean delete(UserStockPool persistentInstance);

	UserStockPool merge(UserStockPool detachedInstance);

	UserStockPool findById(int id);

	List findByExample(UserStockPool instance);
	
	List<String> findByUsername(String username);
	
	boolean hasExist(String username,String stockcode);
	
}