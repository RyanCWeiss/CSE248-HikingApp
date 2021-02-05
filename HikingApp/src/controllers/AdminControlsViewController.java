package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.HikingAppInstance;
import model.Trail;
import model.User;

public class AdminControlsViewController implements Initializable {

		public static HikingAppInstance appInstance;
		private static ObservableList<User> searched;
	
	  	@FXML
	    private TableView<User> userTV;

	    @FXML
	    private TableColumn<User, String> usernameTC;

	    @FXML
	    private TableColumn<User, String> firstnameTC;

	    @FXML
	    private TableColumn<User, String> lastnameTC;
	    
	    @FXML
	    private TableColumn<User, String> phonenumberTC;

	    @FXML
	    private TextField searchTF;

	    @FXML
	    private Button searchuserBTN;

	    @FXML
	    private Button edittrailBTN;

	    @FXML
	    void search(KeyEvent event){
	    	System.out.println("searching");
	    	if (searchTF.getText().isBlank()) {
	    		
	    		return;
	    	}
	    	String query = searchTF.getText().toLowerCase();
	    	LinkedList<User> matches = new LinkedList<User>();
	    	
	    	for (Entry<String, User> 
	        entry : appInstance.getUserContainer().getUserTM().entrySet()) {
				if (entry.getValue().getUsername().toLowerCase().contains(query)) {
					matches.add(entry.getValue());
				}
			}  	
	    	
	    	searched = FXCollections.observableArrayList(matches);
	    	usernameTC.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
	    	firstnameTC.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
			lastnameTC.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
			phonenumberTC.setCellValueFactory(new PropertyValueFactory<User, String>("phoneNumber"));
	        userTV.setItems(searched);
	    }
	    
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
