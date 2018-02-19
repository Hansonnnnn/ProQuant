package data.update;

import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dataservice.recommendedData.BreakthroughService;
import dataservice.recommendedData.ContinuingQuantityService;
import dataservice.recommendedData.ContinuingTrendService;
import dataservice.recommendedData.PeakService;
import dataservice.recommendedData.PriceService;

@Component
public class RecommendedDataUpdate implements Runnable{

	@Autowired
	@Qualifier("BD")
	private BreakthroughService bd;

	@Autowired
	@Qualifier("BU")
	private BreakthroughService bu;

	@Autowired
	@Qualifier("CQD")
	private ContinuingQuantityService cqd;

	@Autowired
	@Qualifier("CQU")
	private ContinuingQuantityService cqu;

	@Autowired
	@Qualifier("CTD")
	private ContinuingTrendService ctd;

	@Autowired
	@Qualifier("CTU")
	private ContinuingTrendService ctu;

	@Autowired
	@Qualifier("PeD")
	private PeakService ped;

	@Autowired
	@Qualifier("PeU")
	private PeakService peu;

	@Autowired
	@Qualifier("PrD")
	private PriceService prd;

	@Autowired
	@Qualifier("PrU")
	private PriceService pru;
	
	public void start() {
		Timer timer = new Timer();

		timer.scheduleAtFixedRate((TimerTask) ctd,0, 86400000);
		timer.scheduleAtFixedRate((TimerTask) ctu,0, 86400000);
		timer.scheduleAtFixedRate((TimerTask) bd,0, 86400000);
		timer.scheduleAtFixedRate((TimerTask) bu,0, 86400000);
		timer.scheduleAtFixedRate((TimerTask) cqd,0, 86400000);
		timer.scheduleAtFixedRate((TimerTask) cqu,0, 86400000);
		timer.scheduleAtFixedRate((TimerTask) ped,0, 86400000);
		timer.scheduleAtFixedRate((TimerTask) peu,0, 86400000);
		timer.scheduleAtFixedRate((TimerTask) prd,0, 86400000);
		timer.scheduleAtFixedRate((TimerTask) pru,0, 86400000);

	}

	@PostConstruct
	public void init(){
		Thread thread=new Thread(this);
		thread.start();
	}
	@Override
	public void run() {
		start();
	}
}
