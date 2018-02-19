package data.spider.update;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.TimerTask;

import org.springframework.stereotype.Service;

@Service("CSDUSBP")
public class CurrentStockDataUpdateSpiderByPython extends TimerTask implements CurrentDataUpdateSpiderService{

	@Override
	public void updateCurrentData() {
		Calendar calendar = Calendar.getInstance();
		Calendar start = Calendar.getInstance();
		start.set(Calendar.HOUR_OF_DAY, 9);
		start.set(Calendar.MINUTE,15);
		Calendar end = Calendar.getInstance();
		end.set(Calendar.HOUR_OF_DAY, 15);
		end.set(Calendar.MINUTE,0);
		
		if (calendar.before(start)||calendar.after(end)) {
			//return;
		}
		String path = null;
		try {
			path = java.net.URLDecoder.decode(getClass().getResource("/").getPath().substring(1),"utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(Calendar.getInstance().getTime());
		String command = "python "+path+"pythonSpider/getCurrentData.py";
		try {
			Process process = Runtime.getRuntime().exec(command);
		
			process.waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		System.out.println(Calendar.getInstance().getTime());
		System.out.println("Stock run=="+command);
		//python C:/Users/凡/git/ProQuant/ProQuant/target/classes/pythonSpider/getCurrentData.py
		//python C:/Users/凡/git/ProQuant/ProQuant/target/test-classes/pythonSpider/getCurrentData.py
	}

	@Override
	public void run() {

		updateCurrentData();
	}

}
