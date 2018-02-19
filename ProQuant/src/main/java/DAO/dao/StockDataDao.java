package DAO.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import PO.StockData;
import PO.StockDataId;
import model.StockPlate;

public interface StockDataDao {

	void persist(StockData transientInstance);

	void attachDirty(StockData instance);

	void attachClean(StockData instance);

	void delete(StockData persistentInstance);

	StockData merge(StockData detachedInstance);

	StockData findById(StockDataId id);

	List findByExample(StockData instance);

	Map<Date,StockData> queryByHql(String code,Date start,Date end);
	
	Map<Date,StockData> queryByHql(String code,int count,Date end);
	
	List<StockData> queryBuHql(StockPlate plate,Date start,Date end);
}