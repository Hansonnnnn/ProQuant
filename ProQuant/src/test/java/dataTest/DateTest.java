package dataTest;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class DateTest {

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, 1, 1, 0, 0,0);
		Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
		Date date = new Date(calendar.getTimeInMillis());
		Timestamp timestamp2 = new Timestamp(calendar.getTimeInMillis());
		Map<Date, String> map = new LinkedHashMap<>();
		map.put(timestamp, "test");
		System.out.println(map.get(date));
		System.out.println(map.get(timestamp2));
		
		
	}
}
