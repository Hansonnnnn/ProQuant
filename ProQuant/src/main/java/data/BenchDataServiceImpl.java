package data;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DAO.dao.BenchCurrentDataDao;
import DAO.dao.BenchDataDao;
import DAO.dao.InfoDataDao;
import PO.BenchCurrentData;
import PO.BenchData;
import PO.InfoData;
import dataservice.BenchDataService;
import model.StockPlate;


/**
 * @author 凡
 *
 */
@Transactional
@Service("BenchDataService")
public class BenchDataServiceImpl implements BenchDataService{
	
	@Autowired
	private BenchDataDao benchDataDao;

	@Autowired
	private InfoDataDao infoDataDao;
	
	@Autowired
	private BenchCurrentDataDao benchCurrentDataDao;

	@Override
	public Map<Date, BenchData> getDataByDateAndPlate(StockPlate plate, Date start, Date end) {
		Map<Date, BenchData> result = benchDataDao.queryByHql(StockPlate.codeOf(plate), start, end);
	
		return result;
	}


	@Override
	public BenchCurrentData getBenchCurrentData(StockPlate plate) {
		String code = StockPlate.codeOf(plate);

		return benchCurrentDataDao.queryByHql(code);
		
	}


	@Override
	public Map<Date, BenchData> getDataByNumAndPlate(Date end, int count, StockPlate plate) {
		String code = StockPlate.codeOf(plate);
		Map<Date, BenchData> map;
		map = benchDataDao.queryByHql(end, count, code);
		
		return map;
	}


	@Override
	public ArrayList<String> getStockListOdBench(StockPlate plate) {
		
		return infoDataDao.getStockListOfBench(plate);
	}



  
}
