package model;

import java.io.Serializable;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.TreeSet;

import javafx.fxml.Initializable;

public class TotalTrailHistory  implements Serializable, Initializable {

	public static HikingAppInstance appInstance;
	
	private LinkedList<TrailHikedInstance> historyLL;		// have to sequential search for filtering?
	private TreeMap<String, TrailHikedInstance> historyTrailNameTM;
	
	
	public TotalTrailHistory() {
		this.historyLL = new LinkedList<TrailHikedInstance>();
	}
	
	
	
	
	
	
	
	/*
	 * filter (boolean) methods for potential use of streams
	 */
	private boolean trailAddressMatch(TrailHikedInstance hike, String address) {
		return (hike.getTrailAddress().contains(address));
		
	}
	private boolean trailNameMatch(TrailHikedInstance hike, String trailName) {
		return (hike.getTrailName().contains(trailName));
	}
	private boolean trailDifficultyMatch(TrailHikedInstance hike, String difficulty) {
		return (hike.getTrailName().contains(difficulty));
	}
	private boolean trailTypeMatch(TrailHikedInstance hike, String type) {
		return (hike.getTrailType().contains(type));
	}
	private boolean trailGainMatch(int val1, int val2, TrailHikedInstance hike) {
		int gain = hike.getTrailElevationGain();
		return ((gain <= val1 && gain >= val2) || (gain >= val1 && gain <= val2));
	}
	private boolean trailLengthMatch(int val1, int val2, TrailHikedInstance hike) {
		int length = hike.getTrailLength();
		return ((length <= val1 && length >= val2) || (length >= val1 && length <= val2));
	}
	private boolean trailUserMatch(TrailHikedInstance hike, String username) {
		return (hike.getUsername().contains(username));
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		appInstance = app.HikingApp.getAppInstance();
	}

}
