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

public class LoginViewController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
    @FXML
    private TextField userNameTF;

    @FXML
    private Button loginBTN;

    @FXML
    private Button registerBTN;

    @FXML
    private PasswordField passwordTF;

    @FXML
    private ImageView loginIV;

    @FXML
    void LogIn(ActionEvent event) {
    	if (validateLoginAttempt()) {
    		logInUser();
    		goHomeView(event);
    	}
    }
    private boolean validateLoginAttempt() {
    	
    	System.out.println("validateLoginAttempt: incomplete");
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

