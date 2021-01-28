package model;

import java.io.Serializable;

public class Admin extends User  implements Serializable{

	String username;
	String firstName;
	String lastName;
	String password;
	String phoneNumber;
	
	
	public Admin(String username, String firstName, String lastName, String password, String phoneNumber) {
		
		super(username,firstName, lastName, password, phoneNumber);
		
	}
	
}
