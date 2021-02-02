package model;

import java.io.Serializable;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class UserHistory  implements Serializable, Initializable {

	
	public static HikingAppInstance appInstance;
	
	private User user;
	private LinkedList<TrailHikedInstance> historyLL;		// small enough to sequential search
	
	public UserHistory(User user) {
		this.user = user;
		this.historyLL = new LinkedList<TrailHikedInstance>();
	}
	
	/*
	 * methods to search the history of a user
	 */
	
	public LinkedList<TrailHikedInstance> searchTrailName(LinkedList<TrailHikedInstance> list, String searchQuery){
		LinkedList<TrailHikedInstance> returnedList = new LinkedList<TrailHikedInstance>();
		for (TrailHikedInstance hike : list) {
			if (hike.getTrailName().contentEquals(searchQuery)) {
				returnedList.add(hike);
			}
		}
		return returnedList;
	}
	public LinkedList<TrailHikedInstance> searchTrailAddress(LinkedList<TrailHikedInstance> list, String searchQuery){
		LinkedList<TrailHikedInstance> returnedList = new LinkedList<TrailHikedInstance>();
		for (TrailHikedInstance hike : list) {
			if (hike.getTrailAddress().contentEquals(searchQuery)) {
				returnedList.add(hike);
			}
		}
		return returnedList;
	}
	public LinkedList<TrailHikedInstance> searchGainRange(LinkedList<TrailHikedInstance> list, int val1, int val2){
		LinkedList<TrailHikedInstance> returnedList = new LinkedList<TrailHikedInstance>();
		for (TrailHikedInstance hike : list) {
			double gain = hike.getTrailElevationGain();
			
			if ((gain <= val1 && gain >= val2) || (gain >= val1 && gain <= val2)) {
				returnedList.add(hike);
			}
		}
		return returnedList;
	}
	public LinkedList<TrailHikedInstance> searchLengthRange(LinkedList<TrailHikedInstance> list, int val1, int val2){
		LinkedList<TrailHikedInstance> returnedList = new LinkedList<TrailHikedInstance>();
		for (TrailHikedInstance hike : list) {
			double length = hike.getTrailLength();
			
			if ((length <= val1 && length >= val2) || (length >= val1 && length <= val2)) {
				returnedList.add(hike);
			}
		}
		return returnedList;
		
	}
	
	/*
	 * If Streams are used...
	 * - have a check that a filter type has been added; if added, apply the filter to the Linked List that will be returned
	 */
//	public LinkedList<TrailHikedInstance> useFilters(){ // params will be the fields that data was entered into	
	
//	}
	
	// or i can create boolean methods and stream them	// LOOK AT FIND A HIKE METHODS
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
		double gain = hike.getTrailElevationGain();
		return ((gain <= val1 && gain >= val2) || (gain >= val1 && gain <= val2));
	}
	private boolean trailLengthMatch(int val1, int val2, TrailHikedInstance hike) {
		double length = hike.getTrailLength();
		return ((length <= val1 && length >= val2) || (length >= val1 && length <= val2));
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		appInstance = app.HikingApp.getAppInstance();
	}
}
