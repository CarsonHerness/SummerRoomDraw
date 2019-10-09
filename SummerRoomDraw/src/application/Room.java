package application;

import java.util.ArrayList;
import java.util.Date;

public class Room {
	// ---------------- SET BY BUILDER ------------------
	private final Dorm dorm;
	private final String roomNumber;
	private final int minCapacity;
	private final int maxCapacity;
	
	// whether or not this room is being used for summer residents
	private boolean summerRoom;
	
	private Date springResidentMoveOutDate;
	private Date fallResidentMoveInDate;

	// -------------- SET IN ROOM CLASS -------------------
	// Updated whenever a summer resident is added
	private Date earliestMoveInDateFirstSummerResidents;
	private Date latestMoveOutDateFirstSummerResidents;
	private Date earliestMoveInDateSecondSummerResidents;
	private Date latestMoveOutDateSecondSummerResidents;
	
	private ArrayList<Person> springResidents;
	private ArrayList<Person> firstSummerResidents;
	private ArrayList<Person> secondSummerResidents;
	private ArrayList<Person> fallResidents;

	// default to false
	private boolean summerMathRoom = false;
	
	// default to normal
	private FallRoomType fallRoomType = FallRoomType.NORMAL;
	
	public static class Builder {
		// Required parameters
		private final Dorm dorm;
		private final String roomNumber;
		private final int minCapacity;
		private final int maxCapacity;
		
		// Optional parameters set to default values
		private boolean summerRoom = false;

		// defaulted to normal values for HMC student
		// Monday, 18 May 2020 0:00:00 GMT
		// Epoch timestamp: 1589760000000 ms
		private Date springResidentMoveOutDate = new Date(1589760000000L);

		// Sunday, 30 August 2020 0:00:00 GMT
		// Epoch timestamp: 1598745600000 ms
		private Date fallResidentMoveInDate = new Date(1598745600000L);
		
		public Builder(Dorm dorm, String roomNumber, int minCapacity, int maxCapacity) {
			this.dorm = dorm;
			this.roomNumber = roomNumber;
			this.minCapacity = minCapacity;
			this.maxCapacity = maxCapacity;
		}
		
		public Builder summerRoom(boolean summerRoom) {
			this.summerRoom = summerRoom;
			return this;
		}
		
		public Builder springResidentMoveOutDate(Date date) {
			this.springResidentMoveOutDate = date;
			return this;
		}
		
		public Builder fallResidentMoveInDate(Date date) {
			this.fallResidentMoveInDate = date;
			return this;
		}
	}
	
	private Room(Builder builder) {
		this.dorm = builder.dorm;
		this.roomNumber = builder.roomNumber;
		this.minCapacity = builder.minCapacity;
		this.maxCapacity = builder.maxCapacity;
		this.summerRoom = builder.summerRoom;
		this.springResidentMoveOutDate = builder.springResidentMoveOutDate;
		this.fallResidentMoveInDate = builder.fallResidentMoveInDate;
	}

	public Dorm getDorm() {
		return dorm;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public FallRoomType getFallRoomType() {
		return fallRoomType;
	}

	public void setFallRoomType(FallRoomType fallRoomType) {
		this.fallRoomType = fallRoomType;
	}

	public ArrayList<Person> getSpringResidents() {
		return springResidents;
	}

	public void addSpringResident(Person person) {
		// verify that there's space
		if (springResidents.size() < maxCapacity) {
			springResidents.add(person);

			// update dates
			Date moveOutDate = person.getSpringMoveOutDate();
			if (moveOutDate.after(springResidentMoveOutDate)) {
				springResidentMoveOutDate = moveOutDate;
			}
		} else {
			throw new ArrayIndexOutOfBoundsException(
					getRoomName() + " is full for Spring. Cannot add " + person.getDisplayName());
		}
	}

	public ArrayList<Person> getFirstSummerResidents() {
		return firstSummerResidents;
	}

	public void addFirstSummerResident(Person person) {
		// verify that there's space
		// allowing for time overlap with Spring Resident because summer researcher can
		// move in as roommate during Summer Math
		// TODO: add Warning if resident's moveInDate is before the springResidentMoveOutDate
		// TODO: add Warning if earliestMoveInDateSecondSummerResidents is before first's MoveOutDate date
		if (firstSummerResidents.size() < maxCapacity) {
			firstSummerResidents.add(person);

			// update dates
			Date moveInDate = person.getFirstHousingStartDate();
			if (earliestMoveInDateFirstSummerResidents == null) {
				earliestMoveInDateFirstSummerResidents = moveInDate;
			} else if (earliestMoveInDateFirstSummerResidents.after(moveInDate)) {
				earliestMoveInDateFirstSummerResidents = moveInDate;
			}
			
			Date moveOutDate = person.getFirstHousingEndDate();
			if (latestMoveOutDateFirstSummerResidents == null) {
				latestMoveOutDateFirstSummerResidents = moveOutDate;
			} else if (latestMoveOutDateFirstSummerResidents.before(moveOutDate)) {
				latestMoveOutDateFirstSummerResidents = moveOutDate;
			}

		} else {
			throw new ArrayIndexOutOfBoundsException(
					getRoomName() + " is full for Summer Room 1. Cannot add " + person.getDisplayName());
		}
	}

	public ArrayList<Person> getSecondSummerResidents() {
		return secondSummerResidents;
	}

	public void addSecondSummerResident(Person person) {
		// verify that there's space
		if (secondSummerResidents.size() < maxCapacity) {
			secondSummerResidents.add(person);

			// update dates
		} else {
			throw new ArrayIndexOutOfBoundsException(
					getRoomName() + " is full for Summer Room 2. Cannot add " + person.getDisplayName());
		}
	}

	public ArrayList<Person> getFallResidents() {
		return fallResidents;
	}

	public void addFallResident(Person person) {
		// verify that there's space
		if (fallResidents.size() < maxCapacity) {
			fallResidents.add(person);

			// update dates
		} else {
			throw new ArrayIndexOutOfBoundsException(
					getRoomName() + " is full for Fall. Cannot add " + person.getDisplayName());
		}
	}

	public int getMinCapacity() {
		return minCapacity;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}
	
	public boolean getSummerRoom() {
		return summerRoom;
	}

	public Date getSpringResidentMoveOutDate() {
		return springResidentMoveOutDate;
	}

	public void setSpringResidentMoveOutDate(Date springResidentMoveOutDate) {
		this.springResidentMoveOutDate = springResidentMoveOutDate;
	}

	public Date getFallResidentMoveInDate() {
		return fallResidentMoveInDate;
	}

	public void setFallResidentMoveInDate(Date fallResidentMoveInDate) {
		this.fallResidentMoveInDate = fallResidentMoveInDate;
	}

	public Date getEarliestMoveInDateFirstSummerResidents() {
		return earliestMoveInDateFirstSummerResidents;
	}

	public void setEarliestMoveInDateFirstSummerResidents(Date earliestMoveInDateFirstSummerResidents) {
		this.earliestMoveInDateFirstSummerResidents = earliestMoveInDateFirstSummerResidents;
	}

	public Date getLatestMoveOutDateFirstSummerResidents() {
		return latestMoveOutDateFirstSummerResidents;
	}

	public void setLatestMoveOutDateFirstSummerResidents(Date latestMoveOutDateFirstSummerResidents) {
		this.latestMoveOutDateFirstSummerResidents = latestMoveOutDateFirstSummerResidents;
	}

	public Date getEarliestMoveInDateSecondSummerResidents() {
		return earliestMoveInDateSecondSummerResidents;
	}

	public void setEarliestMoveInDateSecondSummerResidents(Date earliestMoveInDateSecondSummerResidents) {
		this.earliestMoveInDateSecondSummerResidents = earliestMoveInDateSecondSummerResidents;
	}

	public Date getLatestMoveOutDateSecondSummerResidents() {
		return latestMoveOutDateSecondSummerResidents;
	}

	public void setLatestMoveOutDateSecondSummerResidents(Date latestMoveOutDateSecondSummerResidents) {
		this.latestMoveOutDateSecondSummerResidents = latestMoveOutDateSecondSummerResidents;
	}

	public boolean isSummerMathRoom() {
		return summerMathRoom;
	}

	public void setSummerMathRoom(boolean summerMathRoom) {
		this.summerMathRoom = summerMathRoom;
	}

	public String getRoomName() {
		return dorm.toStringAbbreviation() + roomNumber;
	}
}
