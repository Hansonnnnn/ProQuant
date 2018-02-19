package DAO.dao;

import java.util.List;

import PO.Hs300sData;

public interface Hs300sDataDao {

	void persist(Hs300sData transientInstance);

	void attachDirty(Hs300sData instance);

	void attachClean(Hs300sData instance);

	void delete(Hs300sData persistentInstance);

	Hs300sData merge(Hs300sData detachedInstance);

	Hs300sData findById(long id);

	List findByExample(Hs300sData instance);

}