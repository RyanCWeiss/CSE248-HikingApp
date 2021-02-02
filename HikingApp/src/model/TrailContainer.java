package model;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javafx.fxml.Initializable;

public class TrailContainer  implements Serializable, Initializable {

	public static HikingAppInstance appInstance;
	
	private TreeMap<String, Trail> trailTM;
	
	public TrailContainer(){
		this.trailTM = new TreeMap<String, Trail>();
	}
	
//	private HashMap<trail>	// what will we do to have fast searching? Hashmap for the names or IDs. TreeMap for the rest?
	
// linked hashset for anything that might have key overlap (length, gain, difficulty

// hashmap for address

// distance and elevation gain might be a range instead of a 
	
// return a set of the values that match any keys that were matched 
	
	/*
	 * contains a variation of key (case ignored) 
	 * returns the user matching that key (case ignored)
	 */
	public boolean containsKeyIgnoreCase(String trailName) {	// Since we are putting keys as lowecase, we need to ensure case sensetivity when checking validity of new username
		return trailTM.containsKey(trailName.toLowerCase());
	}
	public Trail getIgnoreCase(String trailName) {
		return trailTM.get(trailName.toLowerCase());
	}
	
	/*
	 * add a new trail
	 */
	public Trail putIfAbsent(Trail trail) {
		if (!this.containsKeyIgnoreCase(trail.getTrailName())) {
			System.out.println("Putting trail...");
			this.put(trail);
			
			return trail;
		} else {
			return null;
		}
	}
	
	/*
	 * -> insert the trail to the map
	 */
	private void put(Trail trail) {
		trailTM.put(trail.getTrailName().toLowerCase(), trail);
		System.out.println("Trail Stored: " + this.getIgnoreCase(trail.getTrailName()).toString() + "...");
	}
	
	public TreeMap<String, Trail> getTrailTM() {
		return trailTM;
	}

	public void setTrailTM(TreeMap<String, Trail> trailTM) {
		this.trailTM = trailTM;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		appInstance = app.HikingApp.getAppInstance();
	}
}
