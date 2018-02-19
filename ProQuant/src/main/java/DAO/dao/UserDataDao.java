package DAO.dao;

import java.util.List;

import PO.user.UserData;

public interface UserDataDao {

	void persist(UserData transientInstance);

	void attachDirty(UserData instance);

	void attachClean(UserData instance);

	void delete(UserData persistentInstance);

	UserData merge(UserData detachedInstance);

	UserData findById(java.lang.String id);

	List findByExample(UserData instance);
	
	List<String> getAllUserName();

}