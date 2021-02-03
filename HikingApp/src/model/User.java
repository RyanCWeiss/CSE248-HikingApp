package model;

import java.io.Serializable;

public class User implements Serializable {

	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private String phoneNumber;
	private UserHistory history;
	private String profilePicString;
	
	public User(String username, String firstName, String lastName, String password, String phoneNumber) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.history = new UserHistory(this);
		this.profilePicString = "/view/initial_profile_picture.jpg";
		
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public UserHistory getHistory() {
		return history;
	}

	public void setHistory(UserHistory history) {
		this.history = history;
	}

	public String getProfilePicString() {
		return profilePicString;
	}
	
	public void setProfilePicString(String profilePicString) {
		this.profilePicString = profilePicString;
	}

	public boolean isAdmin() {
		return (this instanceof Admin);
	}
	
	public String toString() {
		return this.getUsername() + ": (" + this.getFirstName() + " " + this.getLastName() + ") " + this.getPhoneNumber();
	}
}
