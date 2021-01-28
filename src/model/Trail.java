package model;

import java.io.Serializable;

public class Trail implements Serializable {

	
	private String trailName;
	private String address;
	private String elevationGain;
	private String length;
	private String difficulty;
	private String type;
	private String trailID;
	
	public Trail(String trailName, String address, String elevationGain, String length, String difficulty, String type) {
		this.trailName = trailName;
		this.address = address;
		this.elevationGain = elevationGain;
		this.length = length;
		this.difficulty = difficulty;
		this.type = type;
		this.trailID = model.Utilities.generateTrailID();
	}
	
	

	public String getTrailName() {
		return trailName;
	}

	public void setTrailName(String trailName) {
		this.trailName = trailName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getElevationGain() {
		return elevationGain;
	}

	public void setElevationGain(String elevationGain) {
		this.elevationGain = elevationGain;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
