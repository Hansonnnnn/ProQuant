package data.update;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserPrincipalUpdate implements Runnable{

	@Autowired
	UserUpdateService userUpdateService;
	
	public void start() {
		Calendar calendar = Calendar.getInstance();

		Timer timer = new Timer();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date date = calendar.getTime(); // 第一次执行定时任务的时间
		// 如果第一次执行定时任务的时间 小于当前的时间
		// 此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
		if (date.before(new Date())) {
			date = this.addDay(date, 1);
		}
		timer.scheduleAtFixedRate((TimerTask)userUpdateService,date, 86400000);
	
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
