package DAO.dao;

import java.util.List;
import java.util.Map;

import PO.BenchCurrentData;

public interface BenchCurrentDataDao {

	void persist(BenchCurrentData transientInstance);

	void attachDirty(BenchCurrentData instance);

	void attachClean(BenchCurrentData instance);

	void delete(BenchCurrentData persistentInstance);

	BenchCurrentData merge(BenchCurrentData detachedInstance);

	BenchCurrentData findById(java.lang.Integer id);

	List findByExample(BenchCurrentData instance);
	
	BenchCurrentData queryByHql(String code);

}