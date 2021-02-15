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

	
	public TotalTrailHistory() {
		this.historyLL = new LinkedList<TrailHikedInstance>();
	}
	
	
	public void addHike(User user, TrailHikedInstance trailHiked) {
		historyLL.add(trailHiked);
	}
	
	public LinkedList<TrailHikedInstance> getHistory(){
		return this.historyLL;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		appInstance = app.HikingApp.getAppInstance();
	}

}
