package cn.com.bizunited.cp.oa.utils;

import java.util.Date;

public class TimeStampUtil {

	public static Long toTimeStamp(Date date){
		
		return date.getTime();
	}
	
	public static Date toDate(Long timeStamp){
		
		return new Date(timeStamp);
	}
}
