package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Admin;
import model.HikingAppInstance;
import model.Trail;
import model.TrailHikedInstance;
import model.User;

public class HomeViewController implements Initializable {

		public static HikingAppInstance appInstance;
		public static ObservableList<TrailHikedInstance> displayedTrailHistory;
		private static User loggedInUser;
		private TrailHikedInstance selectedTrail;
		
		
	 	@FXML
	    private AnchorPane anchorpane;
	
		@FXML
	    private ImageView profilepicIV;
		
		@FXML
	    private ImageView hikepicIV;

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
	    private TableView<TrailHikedInstance> historyTV;
	    
	    @FXML
	    private TableColumn<TrailHikedInstance, String> trailTC;
	    
	    @FXML
	    private TableColumn<TrailHikedInstance, Double> lengthTC; 
	    
	    @FXML
	    private TableColumn<TrailHikedInstance, String> difficultyTC;
	    
	    @FXML
	    private TableColumn<TrailHikedInstance, String> durationTC;
	    
	    @FXML
	    private TableColumn<TrailHikedInstance, String> paceTC;
	    
	    @FXML
	    private TableColumn<TrailHikedInstance, String> addressTC;
	    
	    @FXML
	    private TableColumn<TrailHikedInstance, LocalDate> dateTC;
	   
	    
	    @FXML 
	    public void selectTrail(MouseEvent event) {
			selectedTrail = historyTV.getSelectionModel().getSelectedItem();
			
			if (selectedTrail != null && selectedTrail.getPic() != null) {
			hikepicIV.setImage(new Image(selectedTrail.getPic()));
			} else {
				hikepicIV.setImage(null);
			}
		}
	    
	    @FXML
	    void updateAccountInfo(ActionEvent event) {
	    	loggedInUser.setFirstName(firstnameTF.getText());
	    	loggedInUser.setLastName(lastnameTF.getText());
	    	loggedInUser.setPassword(passwordTF.getText());
	    	loggedInUser.setPhoneNumber(phonenumberTF.getText());
	    	
	    	System.out.println("updateAccountInfo: incomplete");
	    	
	    }
	    
	    @FXML 
		protected void updatePicture(ActionEvent event) {
		    FileChooser chooser = new FileChooser();
		    chooser.setTitle("Open File");
		    chooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		    Stage stage = (Stage)anchorpane.getScene().getWindow();
		    File file = chooser.showOpenDialog(stage);
		    if (file != null) {
		    	String s = file.getName();
	            System.out.println(s);
	            loggedInUser.setProfilePicString("/resources/" +s);
	           
		    	profilepicIV.setImage(new Image(appInstance.getLoggedInUser().getProfilePicString()));  
	        }
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
		appInstance = app.HikingApp.getAppInstance();
		loggedInUser = appInstance.getLoggedInUser();
		admincontrolsBTN.setVisible(loggedInUser instanceof Admin);
		displayedTrailHistory = FXCollections.observableArrayList(loggedInUser.getHistory().getHistoryLL());
    	trailTC.setCellValueFactory(new PropertyValueFactory<TrailHikedInstance, String>("trailName"));
    	durationTC.setCellValueFactory(new PropertyValueFactory<TrailHikedInstance, String>("duration"));
    	lengthTC.setCellValueFactory(new PropertyValueFactory<TrailHikedInstance, Double>("trailLength"));
        difficultyTC.setCellValueFactory(new PropertyValueFactory<TrailHikedInstance, String>("difficulty"));
        dateTC.setCellValueFactory(new PropertyValueFactory<TrailHikedInstance, LocalDate>("startDate"));
        addressTC.setCellValueFactory(new PropertyValueFactory<TrailHikedInstance, String>("address"));
        paceTC.setCellValueFactory(new PropertyValueFactory<TrailHikedInstance, String>("pace"));
        historyTV.setItems(displayedTrailHistory);
        historyTV.getColumns().get(0).setVisible(false);
        historyTV.getColumns().get(0).setVisible(true);
        
        usernameTF.setText(loggedInUser.getUsername());
        firstnameTF.setText(loggedInUser.getFirstName());
		lastnameTF.setText(loggedInUser.getLastName());
		passwordTF.setText(loggedInUser.getPassword());
		phonenumberTF.setText(loggedInUser.getPhoneNumber());

    	profilepicIV.setImage(new Image(appInstance.getLoggedInUser().getProfilePicString()));
	}

}
