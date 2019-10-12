package application;

import java.util.ArrayList;
import java.util.Date;

public class Person {
	// ----------------- SET BY BUILDER -----------------------
	private final String legalFirstName;
	private String legalMiddleName;
	private final String legalLastName;
	private String preferredName;
	private String email;

	// For Claremont students, this is their Claremont ID. Otherwise, we generate
	// their ID.
	private final String id;
	private final ClassYear classYear;
	private final boolean isHMCStudent;
	private final boolean isClaremontStudent;

	private Date springMoveOutDate;
	private Date fallMoveInDate;

	private Room springRoom;
	private Room fallRoom;
	
	private boolean springSquat;
	private boolean fallSquat;
	
	private Date activityStartDate;
	private Date activityEndDate;
	private Date firstHousingStartDate;
	private Date firstHousingEndDate;
	private Date secondHousingStartDate;
	private Date secondHousingEndDate;

	// ------------ SET BY PERSON CLASS ----------------------
	private ArrayList<Person> firstRoommates = new ArrayList<>();
	private ArrayList<Person> secondRoommates = new ArrayList<>();

	private Room firstSummerRoom;
	private Room secondSummerRoom;

	// Will be generated as integers and converted to doubles to allow for late
	// additions (for numbers such as 3.5)
	private double roomDrawNumber;

	/**
	 * 
	 * @author Carson Herness
	 * 
	 *         Builder class is used to construct all known information about the
	 *         student, before assigning rooms or roommates for the summer. All
	 *         summer roommates and room assignments will be done by the Person
	 *         class.
	 *
	 */
	public static class Builder {
		// Required parameters
		private final String legalFirstName;
		private final String legalLastName;
		private final String id;
		private final ClassYear classYear;
		private final boolean isHMCStudent;
		private final boolean isClaremontStudent;

		// Optional parameters set to default values
		private String legalMiddleName = new String();
		private String email = new String();

		// defaulted to normal values for HMC student
		// Monday, 18 May 2020 0:00:00 GMT
		// Epoch timestamp: 1589760000000 ms
		private Date springMoveOutDate = new Date(1589760000000L);

		// Sunday, 30 August 2020 0:00:00 GMT
		// Epoch timestamp: 1598745600000 ms
		private Date fallMoveInDate = new Date(1598745600000L);
		
		private boolean springSquat = false;
		private boolean fallSquat = false;

		// optional parameters left null if not used
		private Date activityStartDate;
		private Date activityEndDate;

		private Date firstHousingStartDate;
		private Date firstHousingEndDate;

		private Date secondHousingStartDate;
		private Date secondHousingEndDate;

		private Room springRoom;
		private Room fallRoom;

		public Builder(String legalFirstName, String legalLastName, String id, ClassYear classYear,
				boolean isHMCStudent, boolean isClaremontStudent) {
			this.legalFirstName = legalFirstName;
			this.legalLastName = legalLastName;
			this.id = id;
			this.classYear = classYear;
			this.isHMCStudent = isHMCStudent;
			this.isClaremontStudent = isClaremontStudent;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder legalMiddleName(String name) {
			this.legalMiddleName = name;
			return this;
		}

		public Builder springMoveOutDate(Date date) {
			this.springMoveOutDate = date;
			return this;
		}

		public Builder fallMoveInDate(Date date) {
			this.fallMoveInDate = date;
			return this;
		}

		public Builder activityStartDate(Date date) {
			this.activityStartDate = date;
			return this;
		}

		public Builder activityEndDate(Date date) {
			this.activityEndDate = date;
			return this;
		}

		public Builder firstHousingStartDate(Date date) {
			this.firstHousingStartDate = date;
			return this;
		}

		public Builder firstHousingEndDate(Date date) {
			this.firstHousingEndDate = date;
			return this;
		}

		public Builder secondHousingStartDate(Date date) {
			this.secondHousingStartDate = date;
			return this;
		}

		public Builder secondHousingEndDate(Date date) {
			this.secondHousingEndDate = date;
			return this;
		}

		public Builder springRoom(Room room) {
			this.springRoom = room;
			return this;
		}

		public Builder fallRoom(Room room) {
			this.fallRoom = room;
			return this;
		}
		
		public Builder springSquat(boolean springSquat) {
			this.springSquat = springSquat;
			return this;
		}
		
		public Builder fallSquat(boolean fallSquat) {
			this.fallSquat = fallSquat;
			return this;
		}

		public Person build() {
			return new Person(this);
		}
	}

	private Person(Builder builder) {
		this.legalFirstName = builder.legalFirstName;
		this.legalMiddleName = builder.legalMiddleName;
		this.legalLastName = builder.legalLastName;
		this.id = builder.id;
		this.email = builder.email;
		this.classYear = builder.classYear;
		this.isHMCStudent = builder.isHMCStudent;
		this.isClaremontStudent = builder.isClaremontStudent;

		this.springMoveOutDate = builder.springMoveOutDate;
		this.fallMoveInDate = builder.fallMoveInDate;
		this.springSquat = builder.springSquat;
		this.fallSquat = builder.fallSquat;
		this.activityStartDate = builder.activityStartDate;
		this.activityEndDate = builder.activityEndDate;
		this.firstHousingStartDate = builder.firstHousingStartDate;
		this.firstHousingEndDate = builder.firstHousingEndDate;
		this.secondHousingStartDate = builder.secondHousingStartDate;
		this.secondHousingEndDate = builder.secondHousingEndDate;
		this.springRoom = builder.springRoom;
		this.fallRoom = builder.fallRoom;
	}

	public String getLegalFirstName() {
		return legalFirstName;
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

	public ClassYear getClassYear() {
		return classYear;
	}

	public boolean isHMCStudent() {
		return isHMCStudent;
	}

	public boolean isClaremontStudent() {
		return isClaremontStudent;
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

	public ArrayList<Person> getSecondRoomates() {
		return secondRoommates;
	}

	public Room getSpringRoom() {
		return springRoom;
	}

	public Room getFallRoom() {
		return fallRoom;
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
	
	public double getRoomDrawNumber() {
		return roomDrawNumber;
	}
	
	public void setRoomDrawNumber(double num) {
		this.roomDrawNumber = num;
	}
	
	public boolean getFallSquat() {
		return fallSquat;
	}
	
	public boolean getSpringSquat() {
		return springSquat;
	}
}
