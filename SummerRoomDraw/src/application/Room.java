package application;

import java.util.ArrayList;
import java.util.Date;

public class Room {
	Dorm dorm;
	String roomNumber;
	// For summer, triples and quads can be doubles and triples, respectively
	int minCapacity;
	int maxCapacity;

	// default to normal
	FallRoomType fallRoomType = FallRoomType.NORMAL;

	ArrayList<Person> springResidents = new ArrayList<>();
	ArrayList<Person> firstSummerResidents = new ArrayList<>();
	ArrayList<Person> secondSummerResidents = new ArrayList<>();
	ArrayList<Person> fallResidents = new ArrayList<>();

	// defaulted to normal values for HMC student
	// Monday, 18 May 2020 0:00:00 GMT
	// Epoch timestamp: 1589760000000 ms
	Date springResidentMoveOutDate = new Date(1589760000000L);

	// Sunday, 30 August 2020 0:00:00 GMT
	// Epoch timestamp: 1598745600000 ms
	Date fallResidentMoveInDate = new Date(1598745600000L);

	// Updated whenever a summer resident is added
	Date earliestMoveInDateFirstSummerResidents;
	Date latestMoveOutDateFirstSummerResidents;
	Date earliestMoveInDateSecondSummerResidents;
	Date latestMoveOutDateSecondSummerResidents;

	// default to false
	boolean summerMathRoom = false;

	// whether or not this room is being used for summer residents
	boolean summerRoom;

	public Dorm getDorm() {
		return dorm;
	}

	public void setDorm(Dorm dorm) {
		this.dorm = dorm;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
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

	public void setMinCapacity(int minCapacity) {
		this.minCapacity = minCapacity;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
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
