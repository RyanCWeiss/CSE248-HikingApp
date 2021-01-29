package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class Utilities implements Initializable{

	public static HikingAppInstance appInstance;
	public static int idNum;
	
	/*
	 * create unique Identifier for each trail
	 */
	public static String generateTrailID() {
		
		return "Trail-" + appInstance.getIdNum();
	}
	/*
	 * User Creation validations
	 * 		unique username, first and last name are filled, password matches confirm password field, number consists of solely 10 numerical chars
	 */
	
	/*
	 * 	-> validate a new username
	 */
	
	/*
	 * 	-> validate password matches confirm password
	 */
	
	/*
	 * 	-> validate a new ensure the name fields are filled
	 */
	
	/*
	 * 	-> validate a number is 10 digits
	 */
	
	/*
	 * User Log In Validation
	 * 		The username exists, the password matches that of the username
	 */
	
	/*
	 * -> Admin check
	 * 		the instance of user matching the username used when logging in is an instance of the Amdin Class
	 */
	
	
	/*
	 * read and update file
	 */
	
	/*
	 * populate the app with a set of trails upon the initial run of the app
	 */
	
	/*
	 * -> Create a random trail using some set of data
	 */
	
	public static void bootUp() throws Exception {
		
		try( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("src/data/HikingAppObjectFile.dat"))); 
				) {	
			app.HikingApp.appInstance = (HikingAppInstance) ois.readObject();
			
			
			System.out.println("Loading Data File...");	
			System.out.println(app.HikingApp.appInstance);	// for testing
			ois.close();
		}
		catch(IOException ie){
			ie.printStackTrace();
			}
		if (app.HikingApp.appInstance == null) {
			app.HikingApp.appInstance = new HikingAppInstance();
			appInstance = app.HikingApp.appInstance;
//			fillNames();
//			populateBooks();
//			addInitialUser("Jane", "Doe", "Jane", "Doe", "64 Gighard Drive", "1234567890", true);
//			addInitialUser("John", "Doe", "John", "Doe","64 Gighard Drive", "0987654321", false);
//			populateUsers();
			System.out.println("Filling new HikingAppInstance");
			}
	}
	
	public static void updateFile(Serializable data) {
		try( ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get("src/data/HikingAppObjectFile.dat")))
				) 
		{	
			System.out.println(data);
			oos.writeObject(data);
		} catch (IOException e) {
			e.printStackTrace();
		} 
        System.out.println("Updating Data File...");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		appInstance = app.HikingApp.getAppInstance();
	}
}
