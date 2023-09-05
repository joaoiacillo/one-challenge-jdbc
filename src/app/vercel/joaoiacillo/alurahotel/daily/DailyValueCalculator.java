package app.vercel.joaoiacillo.alurahotel.daily;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

public class DailyValueCalculator {
	
	public static final double DAILY_VALUE = 15.85;
	
	public static double calculate(Date initialDate, Date finalDate) {
		
		long initialDateInMs = initialDate.getTime();
		long finalDateInMs = finalDate.getTime();
		
		long timeDiff = Math.abs(finalDateInMs - initialDateInMs);
		long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
		
		return Math.max(DAILY_VALUE, DAILY_VALUE * daysDiff);
		
	}
	
}
