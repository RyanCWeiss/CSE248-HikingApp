package controllers;

import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class HomeViewController implements Initializable {

	 	@FXML
	    private AnchorPane anchorpane;
	
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
	    private TextField addressTF;

	    @FXML
	    private Button createaccountBTN;
	    
	    @FXML
	    private Button admincontrolsBTN;

	    @FXML
	    private Button logoutBTN;

	    @FXML
	    private Button updatepictureBTN;

	    @FXML
	    private TextField usernameTF;
	    
	   
	    @FXML
	    void updateAccountInfo(ActionEvent event) {
	    	
	    	System.out.println("updateAccountInfo: incomplete");
	    	
	    }
	    
	    @FXML
	    void goFindHikeView(ActionEvent event) {
	    	
	    	System.out.println("goFindHikeView: incomplete");
	    	try {
				Parent root;
				root = FXMLLoader.load(getClass().getResource("/view/FindHikeView.fxml"));
				Scene mainScene = new Scene(root);
		        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        primaryStage.setScene(mainScene);
		        primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	
	    @FXML
	    protected void updatePicture(ActionEvent event) {
	    	
	    	System.out.println("updatePicture: incomplete");
	    	FileChooser chooser = new FileChooser();
		    chooser.setTitle("Open File");
		    chooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		    Stage stage = (Stage)anchorpane.getScene().getWindow();
		    File file = chooser.showOpenDialog(stage);
		    if (file != null) {
	            String cwd = System. getProperty("user.dir");
	            String s = new File(cwd).toURI().relativize(file.toURI()).getPath();
	            s = s.substring(4,s.length());	
//	            lib.getLoggedInUser().setProPic(s);
//	            System.out.println( lib.getLoggedInUser().getProPic());
//		    	profilepicIV.setImage(new Image(lib.getLoggedInUser().getProPic()));  
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
	    private void goAdminControlsView(ActionEvent event) {
	    	
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
	    
	    
	    
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
