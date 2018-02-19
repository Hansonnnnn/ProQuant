package bl.helper;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.StockDataServiceImpl;
import dataservice.StockDataService;

public class StockInfoHelper {
	
	
	public static String getStockCode(String str,StockDataService service){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if(!isNum.matches()){
			return service.getCode(str);
		}
		return str;
	}
	
	public  static double format(double d) {
		DecimalFormat dFormat=new DecimalFormat("#.0000");
		String string1=dFormat.format(d);
		return Double .parseDouble(string1);
	}

	public static double format_2(double d){
		DecimalFormat dFormat=new DecimalFormat("#.00");
		String string1=dFormat.format(d);
		return Double .parseDouble(string1);
	}

	public static Date add(Date date, int count) {
		Date newDate = date;
		Calendar calendar = Calendar.getInstance();
		if (count > 0) {
			for (int i = 0; i < Math.abs(count); i++) {
				calendar.setTime(newDate);
				calendar.add(Calendar.DATE, 1);
				newDate = calendar.getTime();
				while (isWeekends(newDate)) {
					calendar.add(Calendar.DATE, 2);
					newDate = calendar.getTime();
				}
			}
		} else {
			for (int i = 0; i < Math.abs(count); i++) {
				calendar.setTime(date);
				calendar.add(Calendar.DATE, -1);
				newDate = calendar.getTime();
				while (isWeekends(newDate)) {
					calendar.add(Calendar.DATE, -2);
					newDate = calendar.getTime();
				}
			}
		}
		return newDate;
	}
	
	public static boolean isWeekends(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			return true;
		else
			return false;
	}
}
