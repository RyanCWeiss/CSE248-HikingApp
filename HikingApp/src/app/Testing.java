package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import model.HikingAppInstance;
import model.User;

public class Testing {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		String startTimeHour = "10";
		String startTimeMin = "10";
		String endTimeHour = "10";
		String endTimeMin = "20";
		
		String startDate = "2021-2-5";
		String endDate = "2021-2-5";
		
		int startingMin = Integer.parseInt(startTimeHour)*60 + Integer.parseInt(startTimeMin);
		int endingMin = Integer.parseInt(endTimeHour)*60 + Integer.parseInt(endTimeMin);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
	    Date firstDate = sdf.parse(startDate.toString());
	    Date secondDate = sdf.parse(endDate.toString());

	    long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
//	    System.out.println(firstDate.ge);
	    
	    long dayDifferenceInMin = diff * 1440;
	    
	    int totalDifferenceInMin = (int)((endingMin - startingMin) + dayDifferenceInMin);
	    int hours = totalDifferenceInMin /60;
	    int min = totalDifferenceInMin % 60;
		
	    System.out.println(totalDifferenceInMin);
		System.out.println( "" + hours + " Hours " + min + " Min");
		
	
		
//		HikingAppInstance app = new HikingAppInstance();
//		User a = new User("a", "a", "a", "a", "a");
//		app.getUserContainer().putIfAbsent(a);
//		
//		app.setLoggedInUser(a);
//		System.out.println(app.getUserContainer().containsKeyIgnoreCase("a"));
//		
//		System.out.println("LoggedInUser: " + app.getLoggedInUser());
//		
//		System.out.println("TrailContainer: " + app.getTrailContainer());
//		
	
	}
}
