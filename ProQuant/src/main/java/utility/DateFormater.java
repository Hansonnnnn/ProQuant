package utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormater {
	
	private static final String second_format = "yyyy-MM-dd HH:mm:ss";
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>(); 
    private static final String day_format = "yyyy-MM-dd";
    private static ThreadLocal<DateFormat> threadLocal2 = new ThreadLocal<DateFormat>(); 
 
    public static DateFormat getSecondFormat()   
    {  
        DateFormat df = threadLocal.get();  
        if(df==null){  
            df = new SimpleDateFormat(second_format);  
            threadLocal.set(df);  
        }  
        return df;  
    }  
    public static DateFormat getDayFormat()   
    {  
        DateFormat df = threadLocal2.get();  
        if(df==null){  
            df = new SimpleDateFormat(day_format);  
            threadLocal.set(df);  
        }  
        return df;  
    }  
    public static String formatSecond(Date date) {
        return getSecondFormat().format(date);
    }

    public static Date parseSecond(String strDate) {
        try {
			return getSecondFormat().parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }   
    public static String formatDay(Date date) {
        return getDayFormat().format(date);
    }

    public static Date parseDay(String strDate) {
        try {
			return getDayFormat().parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
        
    }   

}
