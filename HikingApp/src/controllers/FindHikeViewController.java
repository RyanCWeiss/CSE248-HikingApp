package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.HikingAppInstance;
import model.Trail;
import model.TrailHikedInstance;
import model.User;

public class FindHikeViewController implements Initializable {

	public static HikingAppInstance appInstance;
	public static ObservableList<Trail> displayedTrails;
	private static Trail selectedTrail;
	private static User loggedInUser;
	private static String hikePicString;
	
	@FXML
	private static ObservableList<String> hourList = FXCollections.observableArrayList(
		    "0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
	@FXML
	private static ObservableList<String> minuteList = FXCollections.observableArrayList(
			"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59");
	@FXML 
	private Button addpictureBTN;	
	@FXML 
	private AnchorPane anchorpane;	
    @FXML
    private Button loghikeBTN;
    @FXML
    private DatePicker startdateDP;
    @FXML
    private ChoiceBox<String> starthoursCB;
    @FXML
    private ChoiceBox<String> startminutesCB;
    @FXML
    private DatePicker enddateDP;
    @FXML
    private ChoiceBox<String> endhoursCB;
    @FXML
    private ChoiceBox<String> endminutesCB;
    @FXML
    private TableView<Trail> TrailsTV;
    @FXML
    private Button logoutBTN;
    @FXML
    private Button homeBTN;   
    @FXML
    private ToggleButton easyTB;
    @FXML
    private ToggleButton moderateTB;    
    @FXML
    private ToggleButton hardTB;    
    @FXML
    private ToggleButton loopTB;
    @FXML
    private ToggleButton outandbackTB;
    @FXML
    private ToggleButton pointtopointTB;
    @FXML
    private TextField searchTF;
    @FXML
    private TextField minlengthTF;
    @FXML
    private TextField maxlengthTF;
    @FXML
    private TextField mingainTF;
    @FXML
    private TextField maxgainTF;
    @FXML
    private GridPane loghikeGP;
    @FXML
    private TableView<Trail> trailsTV;
    @FXML
    private TableColumn<Trail, String> trailTC;
    @FXML
    private TableColumn<Trail, String> addressTC;
    @FXML
    private TableColumn<Trail, Double> elevationgainTC;
    @FXML
    private TableColumn<Trail, Double>lengthTC;
    @FXML
    private TableColumn<Trail, String> difficultyTC;
    @FXML
    private TableColumn<Trail, String> typeTC;

    
    
    
    @FXML
	private void addPicture(ActionEvent event) {
	    FileChooser chooser = new FileChooser();
	    chooser.setTitle("Open File");
	    chooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
	    Stage stage = (Stage)anchorpane.getScene().getWindow();
	    File file = chooser.showOpenDialog(stage);
	    if (file != null) {
	    	String s = file.getName();
            System.out.println(s);
            hikePicString =("/resources/" +s);
           
//	    	profilepicIV.setImage(new Image(appInstance.getLoggedInUser().getProfilePicString()));  
        }
	}

    @FXML
    void logHike(ActionEvent event) throws ParseException {
    	System.out.println("logHike: incomplete");
    	
    	
    	TrailHikedInstance trailHiked = new TrailHikedInstance(loggedInUser,startdateDP.getValue(), enddateDP.getValue(), starthoursCB.getValue(), startminutesCB.getValue(), endhoursCB.getValue(), endminutesCB.getValue(), selectedTrail, hikePicString);
    	
    	
    	loggedInUser.getHistory().getHistoryLL().add(trailHiked);
    	appInstance.getTotalTrailHistory().addHike(loggedInUser, trailHiked);
    	clearLogFields();
    	selectedTrail = null;
    	loghikeGP.setDisable(true);
    	System.out.println("logHike: incomplete ");
    	System.out.println(trailHiked.toString());
    }
    public void clearLogFields() {
    	startdateDP.setValue(null);
    	starthoursCB.setValue("Hours");
    	startminutesCB.setValue("Minutes");
    	enddateDP.setValue(null);
    	endhoursCB.setValue("Hours");
    	endminutesCB.setValue("Minutes");
    }
    
    @FXML 
    public void selectTrail(MouseEvent event) {
		selectedTrail = trailsTV.getSelectionModel().getSelectedItem();
		
		if (selectedTrail != null) {
			loghikeGP.setDisable(false);
		} else {
			loghikeGP.setDisable(true);
			
		}
	}
    
    @FXML
    void update(ActionEvent event) {
    	
    	displayedTrails = FXCollections.observableArrayList(search());
    	trailTC.setCellValueFactory(new PropertyValueFactory<Trail, String>("trailName"));
    	addressTC.setCellValueFactory(new PropertyValueFactory<Trail, String>("address"));
		typeTC.setCellValueFactory(new PropertyValueFactory<Trail, String>("type"));
        difficultyTC.setCellValueFactory(new PropertyValueFactory<Trail, String>("difficulty"));
        elevationgainTC.setCellValueFactory(new PropertyValueFactory<Trail, Double>("elevationGain"));
        lengthTC.setCellValueFactory(new PropertyValueFactory<Trail, Double>("length"));
        trailsTV.setItems(displayedTrails);
        trailsTV.getColumns().get(0).setVisible(false);
		trailsTV.getColumns().get(0).setVisible(true);
    	System.out.println("update: incomplete");
    }
    
    @FXML
    void update1(KeyEvent event) {
    	displayedTrails = FXCollections.observableArrayList(search());
    	trailTC.setCellValueFactory(new PropertyValueFactory<Trail, String>("trailName"));
    	addressTC.setCellValueFactory(new PropertyValueFactory<Trail, String>("address"));
    	lengthTC.setCellValueFactory(new PropertyValueFactory<Trail, Double>("length"));
        elevationgainTC.setCellValueFactory(new PropertyValueFactory<Trail, Double>("elevationGain"));
		typeTC.setCellValueFactory(new PropertyValueFactory<Trail, String>("type"));
        difficultyTC.setCellValueFactory(new PropertyValueFactory<Trail, String>("difficulty"));
       
        trailsTV.setItems(displayedTrails);
        trailsTV.getColumns().get(0).setVisible(false);
		trailsTV.getColumns().get(0).setVisible(true);
    	System.out.println("update: incomplete");
    }
    
    public LinkedList<Trail> search(){
    	System.out.println("search: incomplete");
    	
    	LinkedList<Trail> matches = new LinkedList<Trail>();
    	
    	for (Entry<String, Trail> 
        entry : appInstance.getTrailContainer().getTrailTM().entrySet()) {
			if (stackFilters(entry.getValue())) {
				matches.add(entry.getValue());
			}
		}  	
    	return matches;
    }
    
    /*
     * Trail Search supports(name filter, length filter, gain filter, difficulty filter, type filter)
     */
    private boolean stackFilters(Trail trail) {
    	
    	return queryFilter(searchTF, trail) && lengthFilter(minlengthTF, maxlengthTF, trail) && gainFilter(mingainTF, maxgainTF, trail) && difficultyFilter(easyTB, moderateTB, hardTB, trail) && typeFilter(loopTB, outandbackTB, pointtopointTB, trail);
    }
    
    /*
     * title partial match filter
     */
    private boolean queryFilter(TextField searchTF, Trail trail) {
    	if (searchTF.getText().isBlank()) {
    		return true;
    	}
    	String query = searchTF.getText();
    	String [] words = query.split("\\s+");
    	String trailName = trail.getTrailName();
    	for (String w: words) {
    		if (!trailName.contains(w)) {
    			return false;
    		}
    	}
    	return true;
    }
    /*
     * length filter
     */
    private boolean lengthFilter(TextField minlengthTF, TextField maxlengthTF, Trail trail) {
    	
    	double length = trail.getLength();
    	if (minlengthTF.getText().isBlank() && maxlengthTF.getText().isBlank()) {
    		return true;
    	}
    	if (!minlengthTF.getText().isBlank() && !maxlengthTF.getText().isBlank()) {
    		
    		double min = Double.parseDouble(minlengthTF.getText());
    		double max = Double.parseDouble(maxlengthTF.getText());
    		return (length >=min) && (length <= max);
    	}
    	else if (minlengthTF.getText().isBlank() && !maxlengthTF.getText().isBlank()) {
    		double max = Double.parseDouble(maxlengthTF.getText());
    		return (length <= max);
    	}
    	else if (!minlengthTF.getText().isBlank() && maxlengthTF.getText().isBlank()) {
    		double min = Double.parseDouble(minlengthTF.getText());
    		return (length >=min);
    	}
    	else {
    		return true;
    	}
    }
    /*
     * gain filter
     */
    private boolean gainFilter(TextField mingainTF, TextField maxgainTF, Trail trail) {
    	
    	double gain = trail.getElevationGain();
    	if (mingainTF.getText().isBlank() && maxgainTF.getText().isBlank()) {
    		return true;
    	}
    	if (!mingainTF.getText().isBlank() && !maxgainTF.getText().isBlank()) {
    		
    		double min = Double.parseDouble(mingainTF.getText());
    		double max = Double.parseDouble(maxgainTF.getText());
    		return (gain >=min) && (gain <= max);
    	}
    	else if (mingainTF.getText().isBlank() && !maxgainTF.getText().isBlank()) {
    		double max = Double.parseDouble(maxgainTF.getText());
    		return (gain <= max);
    	}
    	else if (!mingainTF.getText().isBlank() && maxgainTF.getText().isBlank()) {
    		double min = Double.parseDouble(mingainTF.getText());
    		return (gain >=min);
    	}
    	else {
    		return true;
    	}
    }
    /*
     * difficulty filter
     */
    private boolean difficultyFilter(ToggleButton easyTB, ToggleButton moderateTB, ToggleButton hardTB, Trail trail) {
    	boolean isEasy = easyTB.isSelected();
    	boolean isModerate = moderateTB.isSelected();
    	boolean isHard = hardTB.isSelected();
    	if ((!isEasy && !isModerate && !isHard) || (isEasy && isModerate && isHard)) {	// no filter or all selected return true
    		return true;
    	}
    	String difficulty = trail.getDifficulty();
    	if ((difficulty.contentEquals("Easy") && (isEasy)) ^ (difficulty.contentEquals("Moderate") && (isModerate)) ^ (difficulty.contentEquals("Hard") && (isHard))) {
    		return true;
    	}
    	return false;
    		
    		
    }
    /*
     * type filter
     */
    private boolean typeFilter(ToggleButton isloopTB, ToggleButton isoutandbackTB,  ToggleButton ispointtopointTB, Trail trail) {
    	boolean isLoop = loopTB.isSelected();
    	boolean isOutAndBack = outandbackTB.isSelected();
    	boolean isPointToPoint = pointtopointTB.isSelected();
    	if ((!isLoop && !isOutAndBack && !isPointToPoint) || (isLoop && isOutAndBack && isPointToPoint)) {	// no filter or all selected return true
    		return true;
    	}
    	String type = trail.getType();
    	if ((type.contentEquals("Out and Back") && (isOutAndBack)) ^ (type.contentEquals("Point to Point") && (isPointToPoint)) ^ (type.contentEquals("Loop") && (isLoop))) {
    		return true;
    	}
    	return false;
    		
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		appInstance = app.HikingApp.getAppInstance();
		selectedTrail = null;
		starthoursCB.setItems(hourList);
		startminutesCB.setItems(minuteList);
		endhoursCB.setItems(hourList);
		endminutesCB.setItems(minuteList);
		loggedInUser = appInstance.getLoggedInUser();
	}

}
