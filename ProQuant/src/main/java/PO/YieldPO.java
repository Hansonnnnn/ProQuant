package PO;
/**
 * 
 * @author yk
 * 用来返回特定的值的
 */
public class YieldPO {
    private String code;
    private double yield;//股票的某个属性的和值
    private double todayPrice;//今天的价格
    
    public YieldPO(){
    	
    }
    
	public YieldPO(String code, double yield,double todayPrice) {
		super();
		this.code = code;
		this.yield = yield;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public double getYield() {
		return yield;
	}
	public void setYield(double yield) {
		this.yield = yield;
	}

	public double getTodayPrice() {
		return todayPrice;
	}

	public void setTodayPrice(double todayPrice) {
		this.todayPrice = todayPrice;
	}
    
}
