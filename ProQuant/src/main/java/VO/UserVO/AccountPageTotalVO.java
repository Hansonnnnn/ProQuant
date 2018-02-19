package VO.UserVO;

import java.util.ArrayList;

/**
 * Created by xiezhenyu on 2017/6/6.
 * 该VO对应于用户中心界面个人账户界面的总（Total）VO
 */
public class AccountPageTotalVO {
    //账户金额等信息
    private AccountVO accountVO;
    //持仓记录
    private ArrayList<OwnedStocksVO> ownedStocksVOs;

	//成交记录
    private ArrayList<DealRecordsVO> dealRecordsVOs;


   
	public AccountPageTotalVO(AccountVO accountVO,ArrayList<OwnedStocksVO> ownedStocksVOs,ArrayList<DealRecordsVO> dealRecordsVOs) {
      this.accountVO=accountVO;
      this.dealRecordsVOs=dealRecordsVOs;
      this.ownedStocksVOs=ownedStocksVOs;
    }

    public AccountVO getAccountVO() {
        return accountVO;
    }

    public void setAccountVO(AccountVO accountVO) {
        this.accountVO = accountVO;
    }

    public ArrayList<OwnedStocksVO> getOwnedStocksVOs() {
		return ownedStocksVOs;
	}

	public void setOwnedStocksVOs(ArrayList<OwnedStocksVO> ownedStocksVOs) {
		this.ownedStocksVOs = ownedStocksVOs;
	}

	 public ArrayList<DealRecordsVO> getDealRecordsVOs() {
			return dealRecordsVOs;
	}

		public void setDealRecordsVOs(ArrayList<DealRecordsVO> dealRecordsVOs) {
			this.dealRecordsVOs = dealRecordsVOs;
		}

}
