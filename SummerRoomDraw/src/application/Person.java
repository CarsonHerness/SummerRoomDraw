package application;

import java.util.ArrayList;
import java.util.Date;

public class Person {
	private String legalFirstName;
	private String legalMiddleName;
	private String legalLastName;
	private String preferredName;
	private String email;
	
	// For Claremont students, this is their Claremont ID. Otherwise, we generate their ID.
	private String id;
	private ClassYear classYear;
	private boolean isHMCStudent;
	private boolean isClaremontStudent;
	
	// defaulted to normal values for HMC student
	// Monday, 18 May 2020 0:00:00 GMT
	// Epoch timestamp: 1589760000000 ms
	Date springMoveOutDate = new Date(1589760000000L);
	
	// Sunday, 30 August 2020 0:00:00 GMT
	// Epoch timestamp: 1598745600000 ms
	Date fallMoveInDate = new Date(1598745600000L);
	
	private Date activityStartDate;
	private Date activityEndDate;
	
	private Date firstHousingStartDate;
	private Date firstHousingEndDate;
	
	private Date secondHousingStartDate;
	private Date secondHousingEndDate;
	
	private ArrayList<Person> firstRoommates;
	private ArrayList<Person> secondRoommates;
	
	private Room springRoom;
	private Room fallRoom;
	private Room firstSummerRoom;
	private Room secondSummerRoom;
	
	public String getLegalFirstName() {
		return legalFirstName;
	}

	public void setLegalFirstName(String legalFirstName) {
		this.legalFirstName = legalFirstName;
	}

	public String getLegalMiddleName() {
		return legalMiddleName;
	}

	public void setLegalMiddleName(String legalMiddleName) {
		this.legalMiddleName = legalMiddleName;
	}

	public String getLegalLastName() {
		return legalLastName;
	}

	public void setLegalLastName(String legalLastName) {
		this.legalLastName = legalLastName;
	}

	public String getPreferredName() {
		return preferredName;
	}

	public void setPreferredName(String preferredName) {
		this.preferredName = preferredName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ClassYear getClassYear() {
		return classYear;
	}

	public void setClassYear(ClassYear classYear) {
		this.classYear = classYear;
	}

	public boolean isHMCStudent() {
		return isHMCStudent;
	}

	public void setHMCStudent(boolean isHMCStudent) {
		this.isHMCStudent = isHMCStudent;
	}

	public boolean isClaremontStudent() {
		return isClaremontStudent;
	}

	public void setClaremontStudent(boolean isClaremontStudent) {
		this.isClaremontStudent = isClaremontStudent;
	}
	
	public Date getSpringMoveOutDate() {
		return springMoveOutDate;
	}
	
	public void setSpringMoveOutDate(Date springMoveOutDate) {
		this.springMoveOutDate = springMoveOutDate;
	}
	
	public Date getFallMoveInDate() {
		return fallMoveInDate;
	}
	
	public void setFallMoveInDate(Date fallMoveInDate) {
		this.fallMoveInDate = fallMoveInDate;
	}

	public Date getActivityStartDate() {
		return activityStartDate;
	}

	public void setActivityStartDate(Date activityStartDate) {
		this.activityStartDate = activityStartDate;
	}

	public Date getActivityEndDate() {
		return activityEndDate;
	}

	public void setActivityEndDate(Date activityEndDate) {
		this.activityEndDate = activityEndDate;
	}

	public void setFirstHousingStartDate(Date firstHousingStartDate) {
		this.firstHousingStartDate = firstHousingStartDate;
	}
	
	public Date getFirstHousingStartDate() {
		return firstHousingStartDate;
	}
	
	public void setFirstHousingEndDate(Date firstHousingEndDate) {
		this.firstHousingEndDate = firstHousingEndDate;
	}
	
	public Date getFirstHousingEndDate() {
		return firstHousingEndDate;
	}
	
	public void setSecondHousingStartDate(Date secondHousingStartDate) {
		this.secondHousingStartDate = secondHousingStartDate;
	}
	
	public Date getSecondHousingStartDate() {
		return secondHousingStartDate;
	}
	
	public void setSecondHousingEndDate(Date secondHousingEndDate) {
		this.secondHousingEndDate = secondHousingEndDate;
	}
	
	public Date getSecondHousingEndDate() {
		return secondHousingEndDate;
	}

	public ArrayList<Person> getFirstRoommates() {
		return firstRoommates;
	}

	public void setFirstRoommates(ArrayList<Person> firstRoommates) {
		this.firstRoommates = firstRoommates;
	}

	public ArrayList<Person> getSecondRoomates() {
		return secondRoommates;
	}

	public void setSecondRoomates(ArrayList<Person> secondRoomates) {
		this.secondRoommates = secondRoomates;
	}

	public Room getSpringRoom() {
		return springRoom;
	}

	public void setSpringRoom(Room springRoom) {
		this.springRoom = springRoom;
	}

	public Room getFallRoom() {
		return fallRoom;
	}

	public void setFallRoom(Room fallRoom) {
		this.fallRoom = fallRoom;
	}

	public Room getFirstSummerRoom() {
		return firstSummerRoom;
	}

	public void setFirstSummerRoom(Room firstSummerRoom) {
		this.firstSummerRoom = firstSummerRoom;
	}

	public Room getSecondSummerRoom() {
		return secondSummerRoom;
	}

	public void setSecondSummerRoom(Room secondSummerRoom) {
		this.secondSummerRoom = secondSummerRoom;
	}
		
	public String getDisplayName() {
		return preferredName + " " + legalLastName;
	}
	
	public void addFirstRoommate(Person roommate) {
		firstRoommates.add(roommate);
	}
	
	public void addSecondRoommate(Person roommate) {
		secondRoommates.add(roommate);
	}
	
	public void removeFirstRoommate(Person roommate) {
		firstRoommates.remove(roommate);
	}
	
	public void removeSecondRoommate(Person roommate) {
		secondRoommates.remove(roommate);
	}
}
