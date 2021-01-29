package model;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javafx.fxml.Initializable;

public class UserContainer  implements Serializable, Initializable {

	public static HikingAppInstance appInstance;
	
	public TreeMap<String, User> userTM;	// <User.username,User pair>
	
	public UserContainer() {
		this.userTM = new TreeMap<String, User>();
	}
	
	/*
	 * contains a variation of key (case ignored) 
	 * returns the user matching that key (case ignored)
	 */
	public boolean containsKeyIgnoreCase(String username) {	// Since we are putting keys as lowecase, we need to ensure case sensetivity when checking validity of new username
		return userTM.containsKey(username.toLowerCase());
	}
	public User getIgnoreCase(String username) {
		return userTM.get(username.toLowerCase());
	}
	
	/*
	 * add a new user
	 */
	public User putIfAbsent(User user) {
		if (!this.containsKeyIgnoreCase(user.getUsername())) {
			this.put(user);
			return user;
		} else {
			return null;
		}
	}
	
	/*
	 * -> insert the user to the map
	 */
	private void put(User user) {
		userTM.put(user.getUsername().toLowerCase(), user);
	}
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		appInstance = app.HikingApp.getAppInstance();
	}
	
	
}
