package app;

import model.HikingAppInstance;
import model.User;

public class Testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HikingAppInstance app = new HikingAppInstance();
		User a = new User("a", "a", "a", "a", "a");
		app.getUserContainer().putIfAbsent(a);
		
		app.setLoggedInUser(a);
		System.out.println(app.getUserContainer().containsKeyIgnoreCase("a"));
		
		System.out.println("LoggedInUser: " + app.getLoggedInUser());
		
		System.out.println("TrailContainer: " + app.getTrailContainer());
		
	}

}
