package model;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class UserContainer  implements Serializable, Initializable {

	public static HikingAppInstance appInstance;
	
	
	
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		appInstance = app.HikingApp.getAppInstance();
	}
	
	
}
