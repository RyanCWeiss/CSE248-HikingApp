package model;

import java.io.Serializable;

public class HikingAppInstance  implements Serializable{

	private UserContainer userContainer;
	
	private TrailContainer trailContainer;
	
	private TotalTrailHistory totalTrailHistory;
	
	private User loggedInUser;
	
	private int idNum;
	
	public HikingAppInstance() {
		this.userContainer = new UserContainer();			// underlying DS: Treemap
		this.trailContainer = new TrailContainer();			// underlying DS: Hashmap?
		this.totalTrailHistory = new TotalTrailHistory();	// underlying DS: LinkedList or multiple TreeMaps
		this.loggedInUser = null;
		this.idNum = 0;
	}

	public int getIdNum() {
		return idNum++;
	}
	
	public UserContainer getUserContainer() {
		return userContainer;
	}

	public void setUserContainer(UserContainer userContainer) {
		this.userContainer = userContainer;
	}

	public TrailContainer getTrailContainer() {
		return trailContainer;
	}

	public void setTrailContainer(TrailContainer trailContainer) {
		this.trailContainer = trailContainer;
	}

	public TotalTrailHistory getTotalTrailHistory() {
		return totalTrailHistory;
	}

	public void setTotalTrailHistory(TotalTrailHistory totalTrailHistory) {
		this.totalTrailHistory = totalTrailHistory;
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
	
}
