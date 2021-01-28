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

public class AdminTrailViewController implements Initializable{

	
    @FXML
    private TableView<?> historyTV;

    @FXML
    private TableColumn<?, ?> trailTC;

    @FXML
    private TableColumn<?, ?> addressTC;

    @FXML
    private TableColumn<?, ?> elevationgainTC;

    @FXML
    private TextField typeTF;

    @FXML
    private TableColumn<?, ?> lengthTC;

    @FXML
    private TableColumn<?, ?> difficultyTC;

    @FXML
    private TextField searchTF;

    @FXML
    private Button searchtrailBTN;

    @FXML
    private Button createnewtrailBTN;

    @FXML
    private Button clearBTN;

    @FXML
    private Button admincontrolsBTN;

    @FXML
    private Button updatetrailBTN;

    @FXML
    private TextField trailnameTF;

    @FXML
    private TextField addressTF;

    @FXML
    private TextField elevationTF;

    @FXML
    private TextField lengthTF;

    @FXML
    private TextField difficultyTF;

    @FXML
    private Button logoutBTN;

    @FXML
    private Button homeBTN;

    @FXML
    void clearTextFields(ActionEvent event) {
    	
    	System.out.println("clearTextFields: incomplete");
    }

    @FXML
    void createNewTrail(ActionEvent event) {


    	System.out.println("createNewTrail: incomplete");
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
    void searchTrail(ActionEvent event) {


    	System.out.println("searchTrail: incomplete");
    }
    
    @FXML
    void updateTrail(ActionEvent event) {


    	System.out.println("updateTrail: incomplete");
    }
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
