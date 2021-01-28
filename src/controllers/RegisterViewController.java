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

public class RegisterViewController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
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
	    	if (validateNewUser()) {
	    		saveNewAccount();
	    		goToHomePageView(event);
	    	}
	    }
	    
	    private void saveNewAccount() {
	    	System.out.println("saveNewAccount: incomplete");
	    }
	    
	    private boolean validateNewUser() {
	    	System.out.println("validateNewUser: incomplete");
	    	return true;
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
