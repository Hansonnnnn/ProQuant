package DAO.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import PO.BenchData;
import PO.BenchDataId;

public interface BenchDataDao {

	void persist(BenchData transientInstance);

	void attachDirty(BenchData instance);

	void attachClean(BenchData instance);

	void delete(BenchData persistentInstance);

	BenchData merge(BenchData detachedInstance);

	BenchData findById(BenchDataId id);

	List findByExample(BenchData instance);

	Map<Date,BenchData> queryByHql(String code,Date start,Date end);

	Map<Date,BenchData> queryByHql(Date end, int count, String code);
}