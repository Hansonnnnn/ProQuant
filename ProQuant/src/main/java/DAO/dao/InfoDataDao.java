package DAO.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import PO.InfoData;
import model.StockPlate;

public interface InfoDataDao {

	void persist(InfoData transientInstance);

	void attachDirty(InfoData instance);

	void attachClean(InfoData instance);

	void delete(InfoData persistentInstance);

	InfoData merge(InfoData detachedInstance);

	InfoData findById(long id);

	List findByExample(InfoData instance);

	InfoData queryByHql(String code);
	
	String getName(String code);
	
	Map<String, String> getCodeList();
	
	ArrayList<String> getStockListOfBench(StockPlate plate);
}