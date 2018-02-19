package dataservice;

import PO.marketInfoPO.MarketInfo;

public interface MarketInfoService {
	
	/**
	 * @TODO：获得当前大盘行情信息
	 * @return
	 */
	public MarketInfo getCurrentMarketInfo();
	
}
