package app;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.HikingAppInstance;

public class HikingApp extends Application {

	public static HikingAppInstance appInstance;
	
	public static void main(String[] args) {
		
		launch(args);
		
	}

	public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
//		scene.getStylesheets().add("/view/style.css");
		
		primaryStage.show();
	}
	
	@Override
	public void init() throws Exception {
		model.Utilities.bootUp();
		System.out.println("container != null?:" + appInstance.getUserContainer() != null);
		
	}
	
	@Override
	public void stop() {									// close hook update data
		appInstance.setLoggedInUser(null);
		
		model.Utilities.updateFile(appInstance);
	}
	
	
	public static HikingAppInstance getAppInstance() {
		return appInstance;
	}
}

