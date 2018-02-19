package data;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import PO.StockBasicIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DAO.dao.InfoDataDao;
import DAO.dao.StockBasicIndexDao;
import DAO.dao.StockCurrentDataDao;
import DAO.dao.StockDataDao;
import PO.InfoData;
import PO.StockCurrentData;
import PO.StockData;
import data.spider.update.CurrentStockDataUpdateSpider;
import dataservice.StockDataService;
import model.StockPlate;
import utility.LetterHelper;

@Transactional
@Service("StockDataService")
public class StockDataServiceImpl implements StockDataService {

	@Autowired
	private StockDataDao stockDataDao;
	@Autowired
	private InfoDataDao infoDataDao;
	@Autowired
	private StockCurrentDataDao stockCurrentDataDao;
	@Autowired
	private StockBasicIndexDao stockBasicIndexDao;
	private Map<String, String> nameToCode;
	
	
	@PostConstruct
	private void init() {
		nameToCode = infoDataDao.getCodeList();
	}
	
	@Override
	public Map<Date, StockData> getBasicDateStock(Date start, Date end, String code) {
		Map<Date, StockData> StockDatas = null;
		StockDatas = stockDataDao.queryByHql(code, start, end);
		
		return StockDatas;
	}
	@Override
	public Map<String,Map<Date, StockData>> getBasicDateStock(Date start, Date end, List<String> codes) {
		Map<String,Map<Date, StockData>> map = new Hashtable<>();
		for (String string : codes) {
			map.put(string, stockDataDao.queryByHql(string, start, end));
			System.out.println(map.size());
		}
		return map;
	}
	
	@Override
	public Map<String, StockData> getSomeStocksByDate(Date date, ArrayList<String> codes) {
		Map<String, StockData> result = new LinkedHashMap<>();
		StockData temp;
		if (codes==null) {
			for (String code : nameToCode.values()) {
				temp = stockDataDao.queryByHql(code, date, date).get(date);
				result.put(temp.getId().getCode(),temp);
			}
			
		}else {
			for (String code : codes) {
				temp = stockDataDao.queryByHql(code, date, date).get(date);
				result.put(temp.getId().getCode(),temp);
			}
		}
		return result;
	}
	@Override
	public Map<Date, StockData> getBasicDateStock(Date end, int count, String code) {
	
		Map<Date, StockData> StockDatas = null;
		StockDatas = stockDataDao.queryByHql(code,count,end);
		
		return StockDatas;
	}
 

	@Override
	public Map<String, Integer> searchStock(String nameOrCode) {
		
		
		Map<String, Integer> map = new LinkedHashMap<>();
		if (nameOrCode.trim().equals("")||nameOrCode==null) {
			return  map;
		}
		if (isNumeric(nameOrCode)) {
			for (String string : nameToCode.keySet()) {
				if (String.valueOf(nameToCode.get(string)).contains(nameOrCode)) {
					map.put(string,Integer.parseInt(nameToCode.get(string)));
				}
			}
		} 
		else if (isLetter(nameOrCode)) {
			LetterHelper letterHelper = new LetterHelper();
			nameOrCode = nameOrCode.toUpperCase();
			String firstLetter;
			for (String string : nameToCode.keySet()) {
				firstLetter = letterHelper.String2Alpha(string);
				if (firstLetter.contains(nameOrCode)) {
					map.put(string, Integer.parseInt(nameToCode.get(string)));
				}
			}
		}
		else {
			
			for(String string: nameToCode.keySet()){
				if (string.contains(nameOrCode)) {
					map.put(string, Integer.parseInt(nameToCode.get(string)));
				}
			}
		}

		return map;
	}

	@Override
	public String getCode(String name) {
		
		return nameToCode.get(name);		 
	}

	@Override
	public String getName(String code) {
		
		return infoDataDao.getName(code);
		 
	}
	
	@Override
	public InfoData getStockInfo(String code) {
		
		return infoDataDao.queryByHql(code);
		 
	}

	
	private boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	private boolean isLetter(String str) {
		Pattern pattern = Pattern.compile("[a-zA-Z]*");
		Matcher isLet = pattern.matcher(str);
		if (!isLet.matches()) {
			return false;
		}
		return true;
	}

	@Override
	public StockCurrentData getStockCurrentData(String code) {
		//return CurrentStockDataUpdateSpider.getResult(code);
		return stockCurrentDataDao.queryByHql(code);
	}
	
	
	@Override
	public Map<String, StockCurrentData> getStockCurrentDataAll() {
		return CurrentStockDataUpdateSpider.getAll();
	}


	@Override
	public ArrayList<StockBasicIndex> getStockBasicIndex(ArrayList<String> list) {
		ArrayList<StockBasicIndex> result = new ArrayList<>();
		for (String code : list) {
			result.add(stockBasicIndexDao.findById(code));
		}
		return result;
	}

	@Override
	public Map<String, Map<Date, StockData>> getStocksOfPlate(Date start, Date end, StockPlate plate) {
		List<StockData> temp = stockDataDao.queryBuHql(plate, start, end);
		System.out.println(temp.size());
		 
		return null;
	}

	
}
