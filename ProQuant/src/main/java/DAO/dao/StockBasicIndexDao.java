package DAO.dao;

import java.util.List;

import PO.StockBasicIndex;

public interface StockBasicIndexDao {

	void persist(StockBasicIndex transientInstance);

	void attachDirty(StockBasicIndex instance);

	void attachClean(StockBasicIndex instance);

	void delete(StockBasicIndex persistentInstance);

	StockBasicIndex merge(StockBasicIndex detachedInstance);

	StockBasicIndex findById(java.lang.String id);

	List findByExample(StockBasicIndex instance);

}