package DAO.dao;

import java.util.List;

import PO.user.UserAccount;

public interface UserAccountDao {

	void persist(UserAccount transientInstance);

	void attachDirty(UserAccount instance);

	void attachClean(UserAccount instance);

	void delete(UserAccount persistentInstance);

	UserAccount merge(UserAccount detachedInstance);

	UserAccount findById(java.lang.String id);

	List findByExample(UserAccount instance);

}