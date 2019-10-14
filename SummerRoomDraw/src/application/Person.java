package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class Person implements Comparable<Person> {
	// ----------------- SET BY BUILDER -----------------------
	private final String legalFirstName;
	private String legalMiddleName;
	private final String legalLastName;
	private String preferredName;
	private String email;

	// For Claremont students, this is their Claremont ID. Otherwise, we generate
	// an ID for them.
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

	private boolean prePlaced;

	// ------------ SET BY PERSON CLASS ----------------------
	private ArrayList<Person> firstRoommates = new ArrayList<>();
	private ArrayList<Person> secondRoommates = new ArrayList<>();

	// must do null check before using these
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

		// Parameters set to default values
		private boolean isHMCStudent = true;
		private boolean isClaremontStudent = true;

		// defaulted to normal values for HMC student
		// Monday, 18 May 2020 0:00:00 GMT
		// Epoch timestamp: 1589760000000 ms
		private Date springMoveOutDate = new Date(1589760000000L);

		// Sunday, 30 August 2020 0:00:00 GMT
		// Epoch timestamp: 1598745600000 ms
		private Date fallMoveInDate = new Date(1598745600000L);

		private boolean springSquat = false;
		private boolean fallSquat = false;
		private boolean prePlaced = false;

		// optional parameters left null if not used
		// must do null check before using
		private Date activityStartDate;
		private Date activityEndDate;

		private Date firstHousingStartDate;
		private Date firstHousingEndDate;

		private Date secondHousingStartDate;
		private Date secondHousingEndDate;

		private Room springRoom;
		private Room fallRoom;

		private String preferredName;

		private String legalMiddleName;
		private String email;

		public Builder(String legalFirstName, String legalLastName, String id, ClassYear classYear) {
			this.legalFirstName = legalFirstName;
			this.legalLastName = legalLastName;
			this.id = id;
			this.classYear = classYear;
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
			// TODO: add Person as springResident to room
			return this;
		}

		public Builder fallRoom(Room room) {
			this.fallRoom = room;
			// TODO: add Person as fallResident to room
			return this;
		}

		public Builder springSquat(boolean springSquat) {
			this.springSquat = springSquat;
			if (springSquat) {
				this.prePlaced = true;
			}
			// person is not automatically added to room for summer yet; this is done during
			// the pre-placement around
			return this;
		}

		public Builder fallSquat(boolean fallSquat) {
			this.fallSquat = fallSquat;
			if (fallSquat) {
				this.prePlaced = true;
			}
			// person is not automatically added to room for summer yet; this is done during
			// the pre-placement around
			return this;
		}

		public Builder preferredName(String name) {
			this.preferredName = name;
			return this;
		}

		public Builder prePlaced(boolean prePlaced) {
			this.prePlaced = prePlaced;
			return this;
		}

		public Builder isHMCStudent(boolean isHMCStudent) {
			this.isHMCStudent = isHMCStudent;
			if (!isHMCStudent) {
				this.prePlaced = true;
			}
			return this;
		}

		public Builder isClaremontStudent(boolean isClaremontStudent) {
			this.isClaremontStudent = isClaremontStudent;
			if (!isClaremontStudent) {
				this.prePlaced = true;
			}
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
		this.preferredName = builder.preferredName;
		this.prePlaced = builder.prePlaced;
	}

	public String getLegalFirstName() {
		return legalFirstName;
	}

	public Optional<String> getLegalMiddleName() {
		return Optional.ofNullable(legalMiddleName);
	}

	public void setLegalMiddleName(String legalMiddleName) {
		this.legalMiddleName = legalMiddleName;
	}

	public String getLegalLastName() {
		return legalLastName;
	}

	public Optional<String> getPreferredName() {
		return Optional.ofNullable(preferredName);
	}

	public void setPreferredName(String preferredName) {
		this.preferredName = preferredName;
	}

	public Optional<String> getEmail() {
		return Optional.ofNullable(email);
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

	public Optional<Date> getActivityStartDate() {
		return Optional.ofNullable(activityStartDate);
	}

	public void setActivityStartDate(Date activityStartDate) {
		this.activityStartDate = activityStartDate;
	}

	public Optional<Date> getActivityEndDate() {
		return Optional.ofNullable(activityEndDate);
	}

	public void setActivityEndDate(Date activityEndDate) {
		this.activityEndDate = activityEndDate;
	}

	public void setFirstHousingStartDate(Date firstHousingStartDate) {
		this.firstHousingStartDate = firstHousingStartDate;
	}

	public Optional<Date> getFirstHousingStartDate() {
		return Optional.ofNullable(firstHousingStartDate);
	}

	public void setFirstHousingEndDate(Date firstHousingEndDate) {
		this.firstHousingEndDate = firstHousingEndDate;
	}

	public Optional<Date> getFirstHousingEndDate() {
		return Optional.ofNullable(firstHousingEndDate);
	}

	public void setSecondHousingStartDate(Date secondHousingStartDate) {
		this.secondHousingStartDate = secondHousingStartDate;
	}

	public Optional<Date> getSecondHousingStartDate() {
		return Optional.ofNullable(secondHousingStartDate);
	}

	public void setSecondHousingEndDate(Date secondHousingEndDate) {
		this.secondHousingEndDate = secondHousingEndDate;
	}

	public Optional<Date> getSecondHousingEndDate() {
		return Optional.ofNullable(secondHousingEndDate);
	}

	public ArrayList<Person> getFirstRoommates() {
		return firstRoommates;
	}

	public ArrayList<Person> getSecondRoommates() {
		return secondRoommates;
	}

	public Optional<Room> getSpringRoom() {
		return Optional.ofNullable(springRoom);
	}

	public Optional<Room> getFallRoom() {
		return Optional.ofNullable(fallRoom);
	}

	public Optional<Room> getFirstSummerRoom() {
		return Optional.ofNullable(firstSummerRoom);
	}

	public void setFirstSummerRoom(Room firstSummerRoom) {
		this.firstSummerRoom = firstSummerRoom;
	}

	public Optional<Room> getSecondSummerRoom() {
		return Optional.ofNullable(secondSummerRoom);
	}

	public void setSecondSummerRoom(Room secondSummerRoom) {
		this.secondSummerRoom = secondSummerRoom;
	}

	public String getDisplayName() {
		if (preferredName != null) {
			return preferredName + " " + legalLastName;
		} else {
			return legalFirstName + " " + legalLastName;
		}
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

	public Optional<Double> getRoomDrawNumber() {
		return Optional.ofNullable(roomDrawNumber);
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

	public boolean getPrePlaced() {
		return prePlaced;
	}

	/**
	 * 
	 * @param prePlaced
	 *            This can be changed in cases such as a Fall Squat or Spring Squat
	 *            cannot be realized
	 */
	public void setPrePlaced(boolean prePlaced) {
		this.prePlaced = prePlaced;
	}

	public boolean getIsClaremontStudent() {
		return isClaremontStudent;
	}

	public boolean getIsHMCStudent() {
		return isHMCStudent;
	}

	@Override
	public int compareTo(Person person) {
		// use IDs to order people
		return id.compareTo(person.getId());
	}
}
