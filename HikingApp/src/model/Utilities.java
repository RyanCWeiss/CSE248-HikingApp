package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.TreeMap;

import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Utilities implements Initializable{

	public static HikingAppInstance appInstance;
	public static int idNum;
	
	/*
	 * create unique Identifier for each trail
	 */
	public static String generateTrailID() {
		
		return "Trail-" + app.HikingApp.getAppInstance().getIdNum();
	}
	/*
	 * User Creation validations
	 * 		unique username, first and last name are filled, password matches confirm password field, number consists of solely 10 numerical chars
	 */
	public static boolean validateNewUser(TextField usernameTF, TextField passwordTF, TextField confirmpasswordTF, TextField firstnameTF, TextField lastnameTF, TextField phonenumberTF) {
		UserContainer users = app.HikingApp.getAppInstance().getUserContainer();
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
		System.out.println(validUsername + " " + validFirstname + " " + validLastname + " " + validPassword + " " + validConfirmPassword + " " +  validPhonenumber);
		
		/*
		 * can display errors if desired
		 */
		if (!validUsername) {
			usernameTF.setText("Invalid Username");
		}
		if (!validFirstname) {
			firstnameTF.setText("First Name can only contain letters");
		}
		if (!validLastname) {
			lastnameTF.setText("First Name can only contain letters");
		}
		if (!validPassword) {
			passwordTF.setText("Invalid Password");
		}
		if (!validConfirmPassword) {
			confirmpasswordTF.setText("Password Missmatch");
		}
		if (!validPhonenumber) {
			phonenumberTF.setText("Phone Number must be 10 digits");
		}
		
		System.out.println("Utilities.validateNewUser: incomplete; display errors to the Scene");
		
		return validUsername && validFirstname && validLastname && validPassword && validConfirmPassword && validPhonenumber;
	}
	/*
	 * 	-> validate a new username
	 */
	private static boolean validateNewUsername(String username) {							// the username is at least 5 chars and only contains letters& digits
		
		UserContainer users = app.HikingApp.getAppInstance().getUserContainer();
		
		if (users.containsKeyIgnoreCase(username.toLowerCase())) {
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
	private static boolean validateNewPassword(String password) {	// password must have >= 1 num, 1 letter, >= 5 chars
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
	private static boolean validateNewPasswordMatch(String password, String confirmPassword) {
		return password.contentEquals(confirmPassword);
	}
	
	/*
	 * 	-> validate a new ensure the name fields are filled
	 */
	private static boolean validateNewFirstName(String firstname){							// the name contains only letters
		if (firstname.length() == 0) {
			return false;
		}
		boolean hasNonAlphabeticChar = false;
		for (int i = 0; i < firstname.length() - 1; i++) {
			char ch = firstname.charAt(i);
			
			if (!Character.isLetter(ch)) {
				hasNonAlphabeticChar = true;
			}
		}
		return !(hasNonAlphabeticChar);
	}
		
	private static boolean validateNewLastName(String lastname){							// the name contains only letters
		if (lastname.length() == 0) {
			return false;
		}
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
	private static boolean validateNewPhoneNumber(String phoneNumber){						// the phone number is composed of 10 digit chars ONLY
		boolean hasNonDigitChar = false;
		boolean hasLength = (phoneNumber.length() == 10);
		
		for (int i = 0; i < phoneNumber.length() - 1; i++) {
			char ch = phoneNumber.charAt(i);
			
			if (!Character.isDigit(ch)) {
				hasNonDigitChar = true;
			}
		}
		return !(hasNonDigitChar) && hasLength;
	}

	
	/*
	 * User Log In Validation
	 * 		The username exists, the password matches that of the username
	 */
	
	public static boolean validateLogin(TextField usernameTF, PasswordField passwordTF) {
		String username = usernameTF.getText();
		String password = passwordTF.getText();
		
		boolean validUsername = validateUsername(username);
		boolean validPassword = validatePassword(username, password);
		
		/*
		 * can display errors if desired***
		 */
		if (!validUsername) {
			usernameTF.setText("invalid Login");
		}
		if (!validPassword) {
			usernameTF.setText("invalid Login");
		}
		
		return validUsername && validPassword;
	}
	
	/*
	 * validate that the username belongs to a user instance stored in the userContainer
	 */
	private static boolean validateUsername(String username) {
		
		UserContainer users = app.HikingApp.getAppInstance().getUserContainer();
		if(users.containsKeyIgnoreCase(username)) {
			if (users.getIgnoreCase(username).getUsername().contentEquals(username)) {
				return true;
			}
		}
		return false;
	}
	/*
	 * validate that the password belongs to the user that the username belongs to (return false if the user doesnt exist)
	 */
	private static boolean validatePassword(String username, String password) {
		
		UserContainer users = app.HikingApp.getAppInstance().getUserContainer();
		User user = null;
		if(users.containsKeyIgnoreCase(username)) {
			user = users.getIgnoreCase(username);
			
			if (user.getUsername().contentEquals(username)) {
				System.out.println(user.getPassword() + " " + password);
				if (user.getPassword().contentEquals(password)) {
					return true;
				}
			} 
		}
		return false;
	}
	
	
	/*
	 * -> Admin check
	 * 		the instance of user matching the username used when logging in is an instance of the Amdin Class// Maybe use Strategy Pattern instead***
	 */
	
	
	
	/*
	 * populate the app with a set of trails upon the initial run of the app
	 */
	private static void populateRandomTrails() {
		TrailContainer trails = app.HikingApp.getAppInstance().getTrailContainer();
		String baseName = "Trail-";
		
		for (int i = 0; i < 500; i++) {
			int j = i/26;
			String name = baseName + ((char)((int)(Math.random() * 26) + 65)) + j;
			int typeInt = (int)(Math.random()*3);
			int difficultyInt = (int)(Math.random()*3);
			String type;
			String difficulty;
			if (typeInt ==0) {
				type = "Loop";
			} else if (typeInt == 1) {
				type = "Out and Back";
			} else {
				type = "Point to Point";
			}
			if (difficultyInt ==0) {
				difficulty = "Easy";
			} else if (difficultyInt == 1) {
				difficulty = "Moderate";
			} else {
				difficulty = "Hard";
			}
			double length = ((int)(Math.random() * 200))/10.0;
			double gain = ((int)(Math.random() * 5000))/10.0;
			trails.putIfAbsent(new Trail(name, "random address that doesnt matter for functionality", gain, length, difficulty, type));
			
		}
	}
	
	
	/*
	 * populate the app with a set of Users upon the initial run of the app
	 */
	private static void populateRandomUsers() throws FileNotFoundException {
		int size = 1000;
		String [][] names = getNames(size);
		
		UserContainer users = app.HikingApp.getAppInstance().getUserContainer();
		for (int i = 0; i < 100; i++) {
			String firstName = names[0][(int)(Math.random()* size)];
			String lastName = names[1][(int)(Math.random()* size)];
			String username = firstName+lastName + (i);
			String password = lastName + i;
			String phoneNumber = "631" + (int)(Math.random()* 10) + "" + (int)(Math.random()* 10) +""+ (int)(Math.random()* 10) + "" + (int)(Math.random()* 10) +""+ (int)(Math.random()* 10) + "" + (int)(Math.random()* 10) +""+ (int)(Math.random()* 10);
			
			User user = new User(username, firstName, lastName, password, phoneNumber);
			users.putIfAbsent(user);
			System.out.println(user);
		}
	}
	/*
	 * create an array of first and last names
	 */
	private static String [][] getNames(int size) throws FileNotFoundException{
		
		String [][] names = new String [2][size];	// generating 1000 first & last names
		String firstNameFileName = "src/data/First_Names.txt";
		String lastNameFileName = "src/data/Last_Names.txt";
		Scanner scFName = new Scanner(new File(firstNameFileName)); 
	    Scanner scLName = new Scanner(new File(lastNameFileName)); 
		int count = 0;
		while (scFName.hasNext() && scLName.hasNextLine() && count < size) { 
			names[0][count] = scFName.nextLine();
		 	names[1][count++] = scLName.nextLine();
		}
		return names;
	}
	
	
	
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
			appInstance.getUserContainer().putIfAbsent(new Manager("Jane", "Jane", "Doe", "Doe", "1234567890" ));
			appInstance.getUserContainer().putIfAbsent(new Admin("Ryanw21", "Ryan", "Weiss", "Ryanw#21", "1234567890" ));
			appInstance.getUserContainer().putIfAbsent(new User("Ryan1", "Ryan", "Weiss", "Ryanw1", "1234567890" ));
			populateRandomUsers();
			populateRandomTrails();
			
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
		System.out.println("Util.appInstance: " + appInstance);
		System.out.println("Util.appInstance.getUserContainer(): " + appInstance.getUserContainer());
	}
}
