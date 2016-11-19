package com.lenovo.push.data.speed.stream.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtil {
	
	public static int diffInSecs() {
		// today    
		Calendar then = new GregorianCalendar();
		// reset hour, minutes, seconds and millis
		then.set(Calendar.HOUR_OF_DAY, 0);
		then.set(Calendar.MINUTE, 0);
		then.set(Calendar.SECOND, 0);
		then.set(Calendar.MILLISECOND, 0);

		// next day
		then.add(Calendar.DAY_OF_MONTH, 2);
		Date now = new Date();
		return (int) ((then.getTime().getTime() - now.getTime()) / 1000); 
	}
	
	public static void main(String[] args) {
		System.out.println(TimeUtil.diffInSecs());

	}

}
