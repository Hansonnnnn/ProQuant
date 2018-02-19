package data.update;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import data.spider.update.BasicDataUpdateSpiderService;
import data.spider.update.BasicStockIndexUpdateSpider;
import data.spider.update.CurrentDataUpdateSpiderService;

@Component
public class BasicStockDataUpdate implements Runnable{
	@Autowired()
	@Qualifier("BSDUS")
	BasicDataUpdateSpiderService basicStockDataUpdateSpider;
	@Autowired()
	@Qualifier("BBDUS")
	BasicDataUpdateSpiderService basicBenchDataUpdateSpider;
	@Autowired
	@Qualifier("BSIUS")
	BasicDataUpdateSpiderService basicStockIndexUpdateSpider;

	@Autowired
	@Qualifier("CBDUS")
	CurrentDataUpdateSpiderService currentBenchDataUpdateSpider;
	@Autowired
	@Qualifier("CSDUS")
	CurrentDataUpdateSpiderService currentStockDataUpdateSpider;
	
	
	public void start() {
		Calendar calendar = Calendar.getInstance();

		Timer timer = new Timer();
		calendar.set(Calendar.HOUR_OF_DAY, 1);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date date = calendar.getTime(); // 第一次执行定时任务的时间
		// 如果第一次执行定时任务的时间 小于当前的时间
		// 此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
		if (date.before(new Date())) {
			date = this.addDay(date, 1);
		}
		timer.scheduleAtFixedRate((TimerTask) basicBenchDataUpdateSpider,date, 86400000);
		timer.scheduleAtFixedRate((TimerTask) basicStockDataUpdateSpider,date, 86400000);
		timer.scheduleAtFixedRate((TimerTask) basicStockIndexUpdateSpider,date, 86400000);
		
		timer.scheduleAtFixedRate((TimerTask) currentBenchDataUpdateSpider,0, 60000);
		timer.scheduleAtFixedRate((TimerTask) currentStockDataUpdateSpider,0, 60000);
/*		timer.scheduleAtFixedRate((TimerTask) basicBenchDataUpdateSpider,date, 2000);
		timer.scheduleAtFixedRate((TimerTask) basicStockDataUpdateSpider,date, 2000);
*/
	}

	private Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
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
