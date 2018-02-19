package DAO.dao;

import java.util.List;

import PO.user.UserStockOwned;

public interface UserStockOwnedDao {

	void persist(UserStockOwned transientInstance);

	void attachDirty(UserStockOwned instance);

	void attachClean(UserStockOwned instance);

	void delete(UserStockOwned persistentInstance);

	UserStockOwned merge(UserStockOwned detachedInstance);

	UserStockOwned findById(PO.user.UserStockOwnedId id);

	List findByExample(UserStockOwned instance);

	List<UserStockOwned> getUserStockOwned(String username);

}