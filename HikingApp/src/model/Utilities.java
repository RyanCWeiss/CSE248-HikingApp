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
import javafx.scene.control.TextField;

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
	public boolean validateNewUser(TextField usernameTF, TextField passwordTF, TextField confirmpasswordTF, TextField firstnameTF, TextField lastnameTF, TextField phonenumberTF) {
		String username = usernameTF.getText();
		String password = passwordTF.getText();
		String confirmPassword = confirmpasswordTF.getText();
		String firstname = firstnameTF.getText();
		String lastname = lastnameTF.getText();
		String phonenumber = phonenumberTF.getText();
		
	
		boolean validUsername = validateNewUsername(username);
		boolean validFirstname = validateNewFirstName(firstname);
		boolean validLastname = validateNewLastName(lastname);
		boolean validPassword = validateNewPassword(password);
		boolean validConfirmPassword = validateNewPasswordMatch(password, confirmPassword);
		boolean validPhonenumber = validateNewPhoneNumber(phonenumber);
		
		/*
		 * can display errors if desired
		 */
		
		
		return validUsername && validFirstname && validLastname && validPassword && validConfirmPassword && validPhonenumber;
	}
	/*
	 * 	-> validate a new username
	 */
	public boolean validateNewUsername(String username) {							// the username is at least 5 chars and only contains letters& digits
		
		UserContainer users = appInstance.getUserContainer();
		
		if (users.containsKeyIgnoreCase(username)) {
			return false;
		}
		if (username.length() < 5) {
			return false;
		}
		for (int i = 0; i < username.length() - 1; i ++) {
			char ch = username.charAt(i);
			if (!((Character.isDigit(ch)) || (Character.isLetter(ch)))) {
				return false;
			}
		}	
		return true;
	}
	
	/*
	 *  -> password is >= 5 chars and has >= 1 letter and >= 1 number
	 * 	-> validate password matches confirm password
	 */
	private boolean validateNewPassword(String password) {	// password must have >= 1 num, 1 letter, >= 5 chars
		boolean hasLetter = false;
		boolean hasNumber = false;
		
		boolean hasLength = (password.length() >= 5);
		
		if (!hasLength) {
			return false;
		}
		for (int i = 0; i < password.length() - 1; i++) {
			char ch = password.charAt(i);
			if (Character.isDigit(ch)) {
				hasNumber = true;
			}
			if (Character.isLetter(ch)) {
				hasLetter = true;
			}
		}
		
		return hasNumber && hasLetter;
	}
	private boolean validateNewPasswordMatch(String password, String confirmPassword) {
		return password.contentEquals(confirmPassword);
	}
	
	/*
	 * 	-> validate a new ensure the name fields are filled
	 */
	private boolean validateNewFirstName(String firstname){							// the name contains only letters
		boolean hasNonAlphabeticChar = false;
		for (int i = 0; i < firstname.length() - 1; i++) {
			char ch = firstname.charAt(i);
			
			if (!Character.isLetter(ch)) {
				hasNonAlphabeticChar = true;
			}
		}
		return !(hasNonAlphabeticChar);
	}
		
	public boolean validateNewLastName(String lastname){							// the name contains only letters
		boolean hasNonAlphabeticChar = false;
		for (int i = 0; i < lastname.length() - 1; i++) {
			char ch = lastname.charAt(i);
			
			if (!Character.isLetter(ch)) {
				hasNonAlphabeticChar = true;
			}
		}
		return !(hasNonAlphabeticChar);
	}
		
	/*
	 * 	-> validate a number is 10 digits
	 */
	public boolean validateNewPhoneNumber(String phoneNumber){						// the phone number is composed of 10 digit chars ONLY
		boolean hasNonDigitChar = false;
		boolean hasLength = (phoneNumber.length() == 10);
		
		for (int i = 0; i < phoneNumber.length() - 1; i++) {
			char ch = phoneNumber.charAt(i);
			
			if (!Character.isLetter(ch)) {
				hasNonDigitChar = true;
			}
		}
		return !(hasNonDigitChar) && hasLength;
	}

	
	/*
	 * User Log In Validation
	 * 		The username exists, the password matches that of the username
	 */
	
	/*
	 * -> Admin check
	 * 		the instance of user matching the username used when logging in is an instance of the Amdin Class
	 */
	
	
	
	/*
	 * populate the app with a set of trails upon the initial run of the app
	 */
	
	/*
	 * -> Create a random trail using some set of data
	 */
	
	/*
	 * read and update file
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
	
	/*
	 * instantiate the variable hikingApp across each class as the same instance from the main 
	 */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		appInstance = app.HikingApp.getAppInstance();
	}
}
