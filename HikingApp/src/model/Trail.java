package model;

import java.io.Serializable;

public class Trail implements Serializable {

	
	private String trailName;
	private String address;
	private double elevationGain;
	private double length;
	private String difficulty;
	private String type;
	private String trailID;
	
	public String getTrailID() {
		return trailID;
	}



	public Trail(String trailName, String address, double elevationGain, double length, String difficulty, String type) {
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

	public double getElevationGain() {
		return elevationGain;
	}

	public void setElevationGain(double elevationGain) {
		this.elevationGain = elevationGain;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
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
	
	public String toString() {
		return trailName + ", Length: " + length + ", Elevation Gain: " + elevationGain + ", Type: " + type + ", Difficulty: " + difficulty;
	}
}
