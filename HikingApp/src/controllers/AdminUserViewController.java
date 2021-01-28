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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AdminUserViewController implements Initializable{

    @FXML
    private ImageView profilepicIV;

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
    private Button createaccountBTN;

    @FXML
    private TextField usernameTF;

    @FXML
    private Button logoutBTN;
    
    @FXML
    private Button homeBTN;

    @FXML
    private Button admincontrolsBTN;

    @FXML
    private Button searchuserBTN;

    @FXML
    private TableView<?> historyTV;

    @FXML
    private TableColumn<?, ?> trailTC;

    @FXML
    private TableColumn<?, ?> addressTC;

    @FXML
    private TableColumn<?, ?> elevationgainTC;

    @FXML
    private TableColumn<?, ?> typeTF;

    @FXML
    private TableColumn<?, ?> lengthTC;

    @FXML
    private TableColumn<?, ?> difficultyTC;

    @FXML
    private TableColumn<?, ?> durationTC;

    @FXML
    void goAdminControlsView(ActionEvent event) {
    	try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/view/AdminControlsView.fxml"));
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
    void searchUser(ActionEvent event) {
    	System.out.println("searchUser: incomplete");
    }

    @FXML
    void updateAccountInfo(ActionEvent event) {
    	System.out.println("updateAccountInfo: incomplete");
    }
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	

}
