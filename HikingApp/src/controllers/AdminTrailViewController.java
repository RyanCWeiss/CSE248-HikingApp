package controllers;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.HikingAppInstance;
import model.Trail;
import model.User;

public class AdminTrailViewController implements Initializable{

	public static HikingAppInstance appInstance;
	private static ObservableList<Trail >displayedTrails;
	private static Trail selectedTrail;
	
	
	@FXML
	private static ObservableList<String> typeList = FXCollections.observableArrayList(
		    "Loop", "Out and Back", "Point to Point");
	@FXML
	private static ObservableList<String> difficultyList = FXCollections.observableArrayList(
		    "Easy", "Moderate", "Hard");
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
    private ChoiceBox<String> typeCB;

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
    private ChoiceBox<String> difficultyCB;

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
    private Button deletetrailBTN;
    
    @FXML
    void clearTextFields(ActionEvent event) {
    	trailnameTF.setText("");
		addressTF.setText("");
		difficultyCB.setValue("Difficulty");;
		typeCB.setValue("Type");
		elevationTF.setText("");
		lengthTF.setText("");
		trailnameTF.setDisable(false);
		selectedTrail = null;
		createnewtrailBTN.setDisable(false);
		deletetrailBTN.setDisable(true);
		updatetrailBTN.setDisable(true);
		System.out.println("clearTextFields: incomplete");
    }
    @FXML
    void deleteTrail(ActionEvent event) {
    	deletetrailBTN.setDisable(true);
		updatetrailBTN.setDisable(true);
		appInstance.getTrailContainer().getTrailTM().remove(selectedTrail.getTrailName().toLowerCase());
		
		displayedTrails = FXCollections.observableArrayList(search());
		trailsTV.setItems(displayedTrails);
		trailsTV.getColumns().get(0).setVisible(false);
		trailsTV.getColumns().get(0).setVisible(true);
		clearTextFields(event);
		System.out.println("deleteTrail: incomplete");
    }
    @FXML
    void updateTrail(ActionEvent event) {
    	Trail trailToModify = selectedTrail;
    	trailToModify.setAddress(addressTF.getText());
    	trailToModify.setElevationGain(Double.parseDouble(elevationTF.getText()));
    	trailToModify.setLength(Double.parseDouble(lengthTF.getText()));
    	trailToModify.setType(typeCB.getValue());
    	trailToModify.setDifficulty(difficultyCB.getValue());
    	
    	displayedTrails = FXCollections.observableArrayList(search());
		trailsTV.setItems(displayedTrails);
		trailsTV.getColumns().get(0).setVisible(false);
		trailsTV.getColumns().get(0).setVisible(true);
		
    	System.out.println("updateTrail: incomplete");
    }
	
    /*
     * check that the fields have valid values
     */
    private boolean checkFields() {
    	boolean trailnameValid = (!trailnameTF.getText().isBlank() && !appInstance.getTrailContainer().containsKeyIgnoreCase(trailnameTF.getText()));
    	boolean addressValid = !trailnameTF.getText().isBlank();
    	
    	boolean difficultyValid = (!difficultyCB.getSelectionModel().getSelectedItem().contentEquals("Difficulty"));
    	boolean typeValid = (!typeCB.getSelectionModel().getSelectedItem().contentEquals("Type"));
    	
    	boolean elevationValid = (!elevationTF.getText().isBlank() && canParseToDouble(elevationTF.getText()));;
    	boolean lengthValid = (!lengthTF.getText().isBlank() && canParseToDouble(lengthTF.getText()));
    	
    	return trailnameValid && addressValid && difficultyValid && typeValid && elevationValid && lengthValid;
    }
    
    /*
     * check if a String can be parsed as a double
     */
    private boolean canParseToDouble(String s) {
    	if (s == null) {
    		return false;
    	}
        try {
            double d = Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    @FXML
    void createNewTrail(ActionEvent event) {
    	if (checkFields()) {
    		String difficulty = difficultyCB.getValue();
    		String type = typeCB.getValue();
    		Trail trail = new Trail(trailnameTF.getText(), addressTF.getText(), Double.parseDouble(elevationTF.getText()), Double.parseDouble(lengthTF.getText()), difficulty, type);
    		appInstance.getTrailContainer().addTrail(trail);
    	} else {
    		System.out.println("Trail invalid");
    	}

    	System.out.println("createNewTrail: incomplete");
    }
    
    @FXML 
    public void selectTrail(MouseEvent event) {
		selectedTrail = trailsTV.getSelectionModel().getSelectedItem();
		
		if (selectedTrail != null) {
			System.out.println("selectTrail: incomplete (add fill data feilds of trail feature.");
			trailnameTF.setText(selectedTrail.getTrailName());
			addressTF.setText(selectedTrail.getAddress());
			difficultyCB.setValue(selectedTrail.getDifficulty());
			typeCB.setValue(selectedTrail.getType());
			elevationTF.setText(selectedTrail.getElevationGain() + "");
			lengthTF.setText(selectedTrail.getLength() + "");
			trailnameTF.setDisable(true);
			createnewtrailBTN.setDisable(true);
			deletetrailBTN.setDisable(false);
			updatetrailBTN.setDisable(false);
		} else {
			trailnameTF.setText("");
			addressTF.setText("");
//			difficultyCB.
//			typeCB.
			elevationTF.setText("");
			lengthTF.setText("");
			trailnameTF.setDisable(false);
			createnewtrailBTN.setDisable(false);
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

    	LinkedList<Trail> nameMatches = new LinkedList<Trail>();
    	LinkedList<Trail> matches = new LinkedList<Trail>();
    	
    	for (Entry<String, LinkedList<Trail>> // for each LL in TM ->  check each entry in LL
    	entry : appInstance.getTrailContainer().getTrailTM().entrySet()) {
			if (queryFilter(entry.getKey())) {
				for (Trail trail : entry.getValue()) {
					nameMatches.add(trail);
				}
			}
		}  	    	
    	for (Trail trail: nameMatches) {
			if (stackFilters(trail)) {
				matches.add(trail);
			}
		}  	
    	return matches;
    }
    
    /*
     * Trail Search supports(name filter, length filter, gain filter, difficulty filter, type filter)
     */
    private boolean stackFilters(Trail trail) {
    	
    	return lengthFilter(minlengthTF, maxlengthTF, trail) && gainFilter(mingainTF, maxgainTF, trail) && difficultyFilter(easyTB, moderateTB, hardTB, trail) && typeFilter(loopTB, outandbackTB, pointtopointTB, trail);
    }
    
    /*
     * title partial match filter
     */
    private boolean queryFilter(String trailName) {
    	if (searchTF.getText().isBlank()) {
    		return true;
    	}
    	String query = searchTF.getText().toLowerCase();
    	String [] words = query.split("\\s+");
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
    	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		appInstance = app.HikingApp.getAppInstance();
		typeCB.setItems(typeList);
		difficultyCB.setItems(difficultyList);
		selectedTrail = null;
	}
	
	
}
