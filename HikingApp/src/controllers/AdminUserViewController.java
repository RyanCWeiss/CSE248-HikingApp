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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Admin;
import model.HikingAppInstance;
import model.Manager;
import model.Trail;
import model.TrailHikedInstance;
import model.User;
import model.UserHistory;

public class AdminUserViewController implements Initializable{

	public static HikingAppInstance appInstance;
	private static User searchedUser;
	public static ObservableList<TrailHikedInstance> displayedTrailHistory;
	public User loggedInUser;
    @FXML
    private ImageView profilepicIV;
    @FXML
    private TextField firstnameTF;
    @FXML
    private TextField lastnameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField phonenumberTF;
    @FXML
    private Button createaccountBTN;    
    @FXML
    private Button clearBTN;
    @FXML
    private Button promoteuserBTN;
    @FXML
    private Button demoteuserBTN;
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
    private Button removeuserBTN;
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
    private TableColumn<TrailHikedInstance, String> addressTC;
    @FXML
    private TableColumn<TrailHikedInstance, String> paceTC;
    @FXML
    private TableColumn<TrailHikedInstance, LocalDate> dateTC;
    @FXML 
    private AnchorPane anchorpane;
    
   @FXML 
   void removeUser(ActionEvent event) {
	   appInstance.getUserContainer().getUserTM().remove(searchedUser.getUsername().toLowerCase());
	   clearUserInfo(event);
   }
    
    @FXML
    void searchUser(ActionEvent event) {
    	if (usernameTF.getText().isBlank()) {
    		return;
    	}
    	if (appInstance.getUserContainer().containsKeyIgnoreCase(usernameTF.getText())) {
    		searchedUser = appInstance.getUserContainer().getIgnoreCase(usernameTF.getText());
    		firstnameTF.setText(searchedUser.getFirstName());
    		lastnameTF.setText(searchedUser.getLastName());
    		passwordTF.setText(searchedUser.getPassword());
    		phonenumberTF.setText(searchedUser.getPhoneNumber());
    		displayedTrailHistory = FXCollections.observableArrayList(searchedUser.getHistory().getHistoryLL());
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
            usernameTF.setDisable(true);
            // logged in as manager (can promote users and demote admins)
            if (loggedInUser instanceof Manager) {
            	if (!(searchedUser instanceof Admin)){
            		promoteuserBTN.setDisable(false);
                } 
            	else if (searchedUser instanceof Admin) {
        		demoteuserBTN.setDisable(false);
                }
            }
            // logged in as admin (can promote users only: if the searched user is a base user, they can be promoted, else nothing happens)
            else if (!(searchedUser instanceof Admin)){
        		promoteuserBTN.setDisable(false);
            }
            if ((loggedInUser instanceof Manager) && !(searchedUser instanceof Manager)) {// manager can always remove users beneath it**
           		removeuserBTN.setDisable(false);
    	   } else  if ((loggedInUser instanceof Admin) && !(searchedUser instanceof Admin)&& !(searchedUser instanceof Manager)) {// manager can always remove users beneath it**
          		removeuserBTN.setDisable(false);
    	   }
    	}
    	System.out.println("searchUser: incomplete");
    }

    @FXML
    void updateAccountInfo(ActionEvent event) {
    	System.out.println("updateAccountInfo: incomplete (ADD COCONVERT USER INSTANCE TO ADMIN");
    	searchedUser.setFirstName(firstnameTF.getText());
    	searchedUser.setLastName(lastnameTF.getText());
    	searchedUser.setPassword(passwordTF.getText());
    	searchedUser.setPhoneNumber(phonenumberTF.getText());
    
    }
	
    @FXML
    void clearUserInfo(ActionEvent event) {
    	System.out.println("clearUserInfo: incomplete");
    	displayedTrailHistory = null;
    	searchedUser = null;
		firstnameTF.setText("");
		lastnameTF.setText("");
		passwordTF.setText("");
		phonenumberTF.setText("");
		displayedTrailHistory = FXCollections.observableArrayList();
    	trailTC.setCellValueFactory(new PropertyValueFactory<TrailHikedInstance, String>("trailName"));
    	durationTC.setCellValueFactory(new PropertyValueFactory<TrailHikedInstance, String>("duration"));
    	lengthTC.setCellValueFactory(new PropertyValueFactory<TrailHikedInstance, Double>("trailLength"));
        difficultyTC.setCellValueFactory(new PropertyValueFactory<TrailHikedInstance, String>("difficulty"));
        dateTC.setCellValueFactory(new PropertyValueFactory<TrailHikedInstance, LocalDate>("startDate"));
        historyTV.setItems(displayedTrailHistory);
        historyTV.getColumns().get(0).setVisible(false);
        historyTV.getColumns().get(0).setVisible(true);
        profilepicIV.setImage(new Image("/resources/initial_profile_picture.jpg"));
		promoteuserBTN.setDisable(true);
		demoteuserBTN.setDisable(true);
		usernameTF.setText("");
		usernameTF.setDisable(false);
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
            searchedUser.setProfilePicString("/resources/" +s);
           
	    	profilepicIV.setImage(new Image(searchedUser.getProfilePicString()));  
        }
	}
    
    @FXML
    void promoteUser(ActionEvent event) {
    	/* create instance of User type desired (Admin) 
    	 * x -> use setters and getters to copy all vals to new instance from original User
    	 *   -> replace the value in the user container at key searchedUser.getUsername.toLowerCase() with the new user instance
    	 * x -> refresh the profile by essentially re-searching the user
    	 */
    	String username = searchedUser.getUsername();
    	String firstName = searchedUser.getFirstName();
    	String lastName = searchedUser.getLastName();
    	String password = searchedUser.getPassword();
    	String phoneNumber = searchedUser.getPhoneNumber();
    	
    	UserHistory hist = searchedUser.getHistory();
    	String proPicString = searchedUser.getProfilePicString();
    	Admin changedUser = new Admin(username, firstName, lastName, password, phoneNumber);
    	changedUser.setProfilePicString(proPicString);
    	changedUser.setHistory(hist);
    	
    	appInstance.getUserContainer().replaceValue(changedUser);
    	searchedUser = changedUser;
    	promoteuserBTN.setDisable(true);
    	refresh();
    	System.out.println("promoteUser: incomplete");
    }
    
    @FXML
    void demoteUser(ActionEvent event) {
    	String username = searchedUser.getUsername();
    	String firstName = searchedUser.getFirstName();
    	String lastName = searchedUser.getLastName();
    	String password = searchedUser.getPassword();
    	String phoneNumber = searchedUser.getPhoneNumber();
    	
    	UserHistory hist = searchedUser.getHistory();
    	String proPicString = searchedUser.getProfilePicString();
    	User changedUser = new User(username, firstName, lastName, password, phoneNumber);
    	changedUser.setProfilePicString(proPicString);
    	changedUser.setHistory(hist);
    	
    	appInstance.getUserContainer().replaceValue(changedUser);
    	searchedUser = changedUser;
    	demoteuserBTN.setDisable(true);
    	refresh();
    	System.out.println("promoteUser: incomplete");
    	
    }
    
    private void refresh() {
    	firstnameTF.setText(searchedUser.getFirstName());
		lastnameTF.setText(searchedUser.getLastName());
		passwordTF.setText(searchedUser.getPassword());
		phonenumberTF.setText(searchedUser.getPhoneNumber());
		displayedTrailHistory = FXCollections.observableArrayList(searchedUser.getHistory().getHistoryLL());
		trailTC.setCellValueFactory(new PropertyValueFactory<TrailHikedInstance, String>("trailName"));
    	durationTC.setCellValueFactory(new PropertyValueFactory<TrailHikedInstance, String>("duration"));
    	lengthTC.setCellValueFactory(new PropertyValueFactory<TrailHikedInstance, Double>("trailLength"));
        difficultyTC.setCellValueFactory(new PropertyValueFactory<TrailHikedInstance, String>("difficulty"));
        dateTC.setCellValueFactory(new PropertyValueFactory<TrailHikedInstance, LocalDate>("startDate"));
        historyTV.setItems(displayedTrailHistory);
        historyTV.getColumns().get(0).setVisible(false);
        historyTV.getColumns().get(0).setVisible(true);
        usernameTF.setDisable(true);
        // logged in as manager (can promote users and demote admins)
        if (loggedInUser instanceof Manager) {
        	if (!(searchedUser instanceof Admin)){
        		promoteuserBTN.setDisable(false);
            } 
        	else if (searchedUser instanceof Admin) {
    		demoteuserBTN.setDisable(false);
            }
        }
        // logged in as admin (can promote users only: if the searched user is a base user, they can be promoted, else nothing happens)
        else if (!(searchedUser instanceof Admin)){
    		promoteuserBTN.setDisable(false);
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
    	appInstance.setLoggedInUser(null);
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		appInstance = app.HikingApp.getAppInstance();
		loggedInUser = appInstance.getLoggedInUser();
		searchedUser = null;
		promoteuserBTN.setDisable(true);
		demoteuserBTN.setDisable(true);
	}
	
	

}
