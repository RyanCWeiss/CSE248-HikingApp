package model;

import java.io.Serializable;
import java.util.Date;

public class TrailHikedInstance implements Serializable{

	private Date date;
	private String trailType;
	private String duration;
	private Trail trail;
	private User user;
	private String username;
	private String trailName;
	private double trailLength;
	private String trailDifficulty;
	private double trailElevationGain;
	private String trailAddress;
	private String trailID;
	
	public TrailHikedInstance(User user, Date date, String duration, Trail trail) {
		
		this.user = user;
		this.username = user.getUsername();
		this.date = date;
		this.duration = duration;
		this.trail = trail;
		this.trailName = trail.getTrailName();
		this.trailLength = trail.getLength();
		this.trailDifficulty = trail.getDifficulty();
		this.trailElevationGain = trail.getElevationGain();
		this.trailAddress = trail.getAddress();
		this.trailAddress = trail.getAddress();
		this.trailID = trail.getTrailID();
		this.trailType = trail.getType();
	
	}
	
	public String getUsername() {
		return username;
	}	
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getTrailType() {
		return trailType;
	}

	public void setTrailType(String trailType) {
		this.trailType = trailType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Trail getTrail() {
		return trail;
	}

	public void setTrail(Trail trail) {
		this.trail = trail;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTrailName() {
		return trailName;
	}

	public void setTrailName(String trailName) {
		this.trailName = trailName;
	}

	public double getTrailLength() {
		return trailLength;
	}

	public void setTrailLength(double trailLength) {
		this.trailLength = trailLength;
	}

	public String getTrailDifficulty() {
		return trailDifficulty;
	}

	public void setTrailDifficulty(String trailDifficulty) {
		this.trailDifficulty = trailDifficulty;
	}

	public double getTrailElevationGain() {
		return trailElevationGain;
	}

	public void setTrailElevationGain(double trailElevationGain) {
		this.trailElevationGain = trailElevationGain;
	}

	public String getTrailAddress() {
		return trailAddress;
	}

	public void setTrailAddress(String trailAddress) {
		this.trailAddress = trailAddress;
	}

	public String getTrailID() {
		return trailID;
	}

	public void setTrailID(String trailID) {
		this.trailID = trailID;
	}
	
	
	
	
	
}
