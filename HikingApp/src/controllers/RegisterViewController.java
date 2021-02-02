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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.HikingAppInstance;
import model.User;

public class RegisterViewController implements Initializable {

	public static HikingAppInstance appInstance;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		appInstance = app.HikingApp.getAppInstance();
	}
	
	    @FXML
	    private TextField usernameTF;

	    @FXML
	    private TextField firstnameTF;

	    @FXML
	    private TextField lastnameTF;

	    @FXML
	    private TextField passwordTF;

	    @FXML
	    private TextField confirmpasswordTF;

	    @FXML
	    private TextField phonenumberTF;

	    @FXML
	    private TextField addressTF;

	    @FXML
	    private Button createaccountBTN;

	    @FXML
	    private Button loginBTN;

	    @FXML
	    private ImageView defaultprofilepicIV;

	    @FXML
	    void createAccount(ActionEvent event) {
	    	if (validateNewUser(usernameTF, passwordTF, confirmpasswordTF, firstnameTF, lastnameTF, phonenumberTF)) {
	    		User user = saveNewAccount(usernameTF, passwordTF, firstnameTF, lastnameTF, phonenumberTF);
	    		appInstance.setLoggedInUser(user);
	    		goToHomePageView(event);
	    	}
	    }
	    
	    private User saveNewAccount(TextField usernameTF, TextField  passwordTF, TextField  firstnameTF, TextField  lastnameTF, TextField  phonenumberTF) {
	    	String username = usernameTF.getText();
	    	String password = passwordTF.getText();
	    	String firstname = firstnameTF.getText();
	    	String lastname = lastnameTF.getText();
	    	String phonenumber = phonenumberTF.getText();
	    	
	    	User user = new User(username, password, firstname, lastname, phonenumber);
	    	appInstance.getUserContainer().putIfAbsent(user);
	    	
	    	System.out.println("saveNewAccount: incomplete");
	    	return user;
	    }
	    
	    private boolean validateNewUser(TextField usernameTF, TextField  passwordTF, TextField  confirmpasswordTF, TextField  firstnameTF, TextField  lastnameTF, TextField  phonenumberTF) {
	    	System.out.println("validateNewUser: incomplete");
	    	return model.Utilities.validateNewUser(usernameTF, passwordTF, confirmpasswordTF, firstnameTF, lastnameTF, phonenumberTF);
	    	
	    }
	    
	    private void goToHomePageView(ActionEvent event) {
	    	
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

	}
