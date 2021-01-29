package model;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class TrailContainer  implements Serializable, Initializable {

	public static HikingAppInstance appInstance;
	
//	private HashMap<trail>	// what will we do to have fast searching? Hashmap for the names or IDs. TreeMap for the rest?
	
// linked hashset for anything that might have key overlap (length, gain, difficulty

// hashmap for address

// distance and elevation gain might be a range instead of a 
	
// return a set of the values that match any keys that were matched 
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		appInstance = app.HikingApp.getAppInstance();
	}
}
