package model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TrailHikedInstance implements Serializable{

	private LocalDate startDate;
	private LocalDate endDate;
	private String startTime;
	private String endTime;
	private String trailType;
	private String duration;
	private String difficulty;
	private Trail trail;
	private User user;
	private String username;
	private String trailName;
	private double trailLength;
	private String trailDifficulty;
	private double trailElevationGain;
	private String trailAddress;
	private String trailID;
	private String pace;
	private String pic;
	private String address;
	
	
	public TrailHikedInstance(User user, LocalDate startDate, LocalDate endDate, String startTimeHour, String startTimeMin, String endTimeHour, String endTimeMin, Trail trail, String pic) throws ParseException {

		this.pic = pic;
		this.user = user;
		this.username = user.getUsername();
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTimeHour +":" + startTimeMin;
		this.endTime = endTimeHour +":" + endTimeMin;
		this.duration = calculateDuration(startDate, endDate, startTimeHour, startTimeMin, endTimeHour, endTimeMin);
		this.trail = trail;
		this.trailName = trail.getTrailName();
		this.trailLength = trail.getLength();
		this.trailDifficulty = trail.getDifficulty();
		this.trailElevationGain = trail.getElevationGain();
		this.trailAddress = trail.getAddress();
		this.trailAddress = trail.getAddress();
		this.trailID = trail.getTrailID();
		this.trailType = trail.getType();
		this.difficulty = trail.getDifficulty();
		this.address = trail.getAddress();
		this.pace = ((int)(calculateDurationInt(startDate, endDate, startTimeHour, startTimeMin, endTimeHour, endTimeMin))*100)/100.0 /(trailLength) + " min/mi";
	
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String toString() {
		return "Trail: " + trailName + " User: " + this.user.getUsername() + " Time: " + duration + " Pace: " + pace;
	}
	
	private String calculateDuration(LocalDate startDate, LocalDate endDate, String startTimeHour, String startTimeMin, String endTimeHour, String endTimeMin) throws ParseException {
		int totalDifferenceInMin = calculateDurationInt(startDate, endDate, startTimeHour, startTimeMin, endTimeHour, endTimeMin);
	    int hours = totalDifferenceInMin /60;
	    int min = totalDifferenceInMin % 60;
		
		return ( "" + hours + " Hours " + min + " Min");
	}
	private int calculateDurationInt(LocalDate startDate, LocalDate endDate, String startTimeHour, String startTimeMin, String endTimeHour, String endTimeMin) throws ParseException {
		int startingMin = Integer.parseInt(startTimeHour)*60 + Integer.parseInt(startTimeMin);
		int endingMin = Integer.parseInt(endTimeHour)*60 + Integer.parseInt(endTimeMin);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
	    Date firstDate = sdf.parse(startDate.toString());
	    Date secondDate = sdf.parse(endDate.toString());

	    long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    
	    long dayDifferenceInMin = diff * 1440;
	    
	    return (int)((endingMin - startingMin) + dayDifferenceInMin);
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPace() {
		return pace;
	}

	public void setPace(String pace) {
		this.pace = pace;
	}

	public String getTrailID() {
		return trailID;
	}

	public void setTrailID(String trailID) {
		this.trailID = trailID;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	
	
	
	
	
}
