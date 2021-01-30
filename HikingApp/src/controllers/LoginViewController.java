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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.HikingAppInstance;
import model.User;
import model.UserContainer;

public class LoginViewController implements Initializable {

	static HikingAppInstance appInstance;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		appInstance = app.HikingApp.getAppInstance();
	}
	
	
    @FXML
    private TextField usernameTF;

    @FXML
    private Button loginBTN;

    @FXML
    private Button registerBTN;

    @FXML
    private PasswordField passwordTF;

    @FXML
    private ImageView loginIV;

    @FXML
    void logIn(ActionEvent event) {
    	if (validateLoginAttempt(usernameTF,passwordTF)) {
    		logInUser();
    		goHomeView(event);
    	}
    }
    private boolean validateLoginAttempt(TextField usernameTF, PasswordField passwordTF) {
    	
    	System.out.println("validateLoginAttempt: incomplete		(Set to always return true)");
    	
    	
    	String username = usernameTF.getText();
    	String password = passwordTF.getText();
    	UserContainer userContainer = appInstance.getUserContainer();
    	if (userContainer.containsKeyIgnoreCase(username)) {
    		if (userContainer.getIgnoreCase(username).getUsername().contentEquals(username)) {
    			User user = userContainer.getIgnoreCase(username);
    			if (user.getPassword().contentEquals(password)) {
    				return true;
    			}
    		}
    	}
    	return true;
    }
    private void logInUser() {
    	
    	System.out.println("logInUser: incomplete");
    }
    private void goHomeView(ActionEvent event) {
    	
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
    void Register(ActionEvent event) {
    	
    	goRegisterView(event);
    }
    
    private void goRegisterView(ActionEvent event) {
    	
    	try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/view/RegisterView.fxml"));
			Scene mainScene = new Scene(root);
	        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        primaryStage.setScene(mainScene);
	        primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	

}

