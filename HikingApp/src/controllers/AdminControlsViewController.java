package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.HikingAppInstance;

public class AdminControlsViewController implements Initializable {

		public static HikingAppInstance appInstance;
	
	  	@FXML
	    private TableView<?> historyTV;

	    @FXML
	    private TableColumn<?, ?> trailnameTC;

	    @FXML
	    private TableColumn<?, ?> usernameTC;

	    @FXML
	    private TableColumn<?, ?> durationTC;

	    @FXML
	    private Button searchtrailBTN;

	    @FXML
	    private TextField searchTF;

	    @FXML
	    private Button searchuserBTN;

	    @FXML
	    private Button searchuserBTN1;

	    @FXML
	    private void logOut(ActionEvent event) {
	    	
	    	System.out.println("logOut: incomplete");
	    	logOutUser();
	    	goToLoginView(event);
	    	
	    }
	    private void logOutUser() {
	    	
	    	System.out.println("logOutUser: incomplete");
	    }
	    private void goToLoginView(ActionEvent event) {
	    	
	    	try {
				Parent root;
				root = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
				Scene mainScene = new Scene(root);
		        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        primaryStage.setScene(mainScene);
		        primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    @FXML
	    void goAdminTrailView(ActionEvent event) {
	    	try {
				Parent root;
				root = FXMLLoader.load(getClass().getResource("/view/AdminTrailView.fxml"));
				Scene mainScene = new Scene(root);
		        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        primaryStage.setScene(mainScene);
		        primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	    @FXML
	    void goAdminUserView(ActionEvent event) {
	    	try {
				Parent root;
				root = FXMLLoader.load(getClass().getResource("/view/AdminUserView.fxml"));
				Scene mainScene = new Scene(root);
		        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        primaryStage.setScene(mainScene);
		        primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    @FXML
	    void goHomeView(ActionEvent event) {
	    	try {
				Parent root;
				root = FXMLLoader.load(getClass().getResource("/view/HomeView.fxml"));
				Scene mainScene = new Scene(root);
		        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        primaryStage.setScene(mainScene);
		        primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	    @FXML
	    void searchTrail(ActionEvent event) {
	    	System.out.println("searchTrail: incomplete");
	    }
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		appInstance = app.HikingApp.getAppInstance();
	}
	
	
	

}
