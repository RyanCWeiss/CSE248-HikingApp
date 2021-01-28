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
	 * read and update file
	 */
	
	public static void bootUp() throws Exception {
		
		try( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("data/HikingAppObjectFile.dat"))); 
				) {	
			app.HikingApp.appInstance = (HikingAppInstance) ois.readObject();
			ois.close();
			
			System.out.println("Loading Data File...");	
			System.out.println(app.HikingApp.appInstance);	// for testing
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
		try( ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get("data/HikingAppObjectFile.dat")))
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
