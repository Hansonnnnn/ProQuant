package bl.helper.strategy;

import java.util.Date;
import java.util.Map;

public class DatesAndBase {
	private Date dates[];
	private Map<Date, Double > baseearning;
	
	public DatesAndBase(Date[] dates, Map<Date, Double> baseearning) {
		super();
		this.dates = dates;
		this.baseearning = baseearning;
	}

	public Date[] getDates() {
		return dates;
	}

	public void setDates(Date[] dates) {
		this.dates = dates;
	}

	public Map<Date, Double> getBaseearning() {
		return baseearning;
	}

	public void setBaseearning(Map<Date, Double> baseearning) {
		this.baseearning = baseearning;
	}
	
	
}
