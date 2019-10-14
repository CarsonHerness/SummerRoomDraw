package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class Room {
	// ---------------- SET BY BUILDER ------------------
	private final Dorm dorm;
	private final String roomNumber;
	private final int minCapacity;
	private final int maxCapacity;

	// whether or not this room is being used for summer residents
	private boolean summerRoom;

	private Date latestSpringResidentMoveOutDate;
	private Date earliestFallResidentMoveInDate;

	// -------------- SET IN ROOM CLASS -------------------
	// Updated whenever a summer resident is added
	private Date earliestMoveInDateFirstSummerResidents;
	private Date latestMoveOutDateFirstSummerResidents;
	private Date earliestMoveInDateSecondSummerResidents;
	private Date latestMoveOutDateSecondSummerResidents;

	private ArrayList<Person> springResidents = new ArrayList<>();
	private ArrayList<Person> firstSummerResidents = new ArrayList<>();
	private ArrayList<Person> secondSummerResidents = new ArrayList<>();
	private ArrayList<Person> fallResidents = new ArrayList<>();

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

		public Builder(Dorm dorm, String roomNumber, int maxCapacity) {
			this.dorm = dorm;
			this.roomNumber = roomNumber;
			this.maxCapacity = maxCapacity;

			// Triples can be doubles, Quads can be triples
			if (maxCapacity > 2) {
				this.minCapacity = maxCapacity - 1;
			} else {
				this.minCapacity = maxCapacity;
			}
		}

		public Builder summerRoom(boolean summerRoom) {
			this.summerRoom = summerRoom;
			return this;
		}

		public Room build() {
			return new Room(this);
		}
	}

	private Room(Builder builder) {
		this.dorm = builder.dorm;
		this.roomNumber = builder.roomNumber;
		this.minCapacity = builder.minCapacity;
		this.maxCapacity = builder.maxCapacity;
		this.summerRoom = builder.summerRoom;
		this.latestSpringResidentMoveOutDate = builder.springResidentMoveOutDate;
		this.earliestFallResidentMoveInDate = builder.fallResidentMoveInDate;
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
			if (moveOutDate.after(latestSpringResidentMoveOutDate)) {
				latestSpringResidentMoveOutDate = moveOutDate;
			} else {
				
			}
		} else {
			// this is not a flexible rule that can be overrode, so throws an exception
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
		// TODO: add Warning if resident's moveInDate is before the
		// springResidentMoveOutDate
		// TODO: add Warning if earliestMoveInDateSecondSummerResidents is before
		// first's MoveOutDate date
		// TODO: add roommates to each person in room
		if (firstSummerResidents.size() < maxCapacity) {
			firstSummerResidents.add(person);

			// update dates
			Date moveInDate = person.getFirstHousingStartDate().get();
			if (earliestMoveInDateFirstSummerResidents == null) {
				earliestMoveInDateFirstSummerResidents = moveInDate;
			} else if (earliestMoveInDateFirstSummerResidents.after(moveInDate)) {
				earliestMoveInDateFirstSummerResidents = moveInDate;
			}

			Date moveOutDate = person.getFirstHousingEndDate().get();
			if (latestMoveOutDateFirstSummerResidents == null) {
				latestMoveOutDateFirstSummerResidents = moveOutDate;
			} else if (latestMoveOutDateFirstSummerResidents.before(moveOutDate)) {
				latestMoveOutDateFirstSummerResidents = moveOutDate;
			}

		} else {
			// This is not flexible. If roommates are changing (so it lookes like there can
			// be more than the maximum number of roommates), then use firstSummerResidents
			// and secondSummerResidents
			throw new ArrayIndexOutOfBoundsException(
					getRoomName() + " is full for Summer Room 1. Cannot add " + person.getDisplayName());
		}
	}
	
	public void removeFirstSummerResident(Person person) {
		// TODO: implement: update dates and roommates
	}

	public ArrayList<Person> getSecondSummerResidents() {
		return secondSummerResidents;
	}

	public void addSecondSummerResident(Person person) {
		// verify that there's space
		if (secondSummerResidents.size() < maxCapacity) {
			secondSummerResidents.add(person);

			// TODO: update dates and roommates
		} else {
			// This is not flexible, so it throws an exception rather than creating a Warning
			throw new ArrayIndexOutOfBoundsException(
					getRoomName() + " is full for Summer Room 2. Cannot add " + person.getDisplayName());
		}
	}
	
	public void removeSecondSummerResident(Person person) {
		// TODO: implement: make sure to update dates and roommates
	}

	public ArrayList<Person> getFallResidents() {
		return fallResidents;
	}

	public void addFallResident(Person person) {
		// verify that there's space
		if (fallResidents.size() < maxCapacity) {
			fallResidents.add(person);

			// TODO: update dates and roommates
		} else {
			// This is not flexible, so it throws an exception rather than creating a Warning
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
		return latestSpringResidentMoveOutDate;
	}

	public void setSpringResidentMoveOutDate(Date springResidentMoveOutDate) {
		this.latestSpringResidentMoveOutDate = springResidentMoveOutDate;
	}

	public Date getFallResidentMoveInDate() {
		return earliestFallResidentMoveInDate;
	}

	public void setFallResidentMoveInDate(Date fallResidentMoveInDate) {
		this.earliestFallResidentMoveInDate = fallResidentMoveInDate;
	}

	public Optional<Date> getEarliestMoveInDateFirstSummerResidents() {
		return Optional.ofNullable(earliestMoveInDateFirstSummerResidents);
	}

	public void setEarliestMoveInDateFirstSummerResidents(Date earliestMoveInDateFirstSummerResidents) {
		this.earliestMoveInDateFirstSummerResidents = earliestMoveInDateFirstSummerResidents;
	}

	public Optional<Date> getLatestMoveOutDateFirstSummerResidents() {
		return Optional.ofNullable(latestMoveOutDateFirstSummerResidents);
	}

	public void setLatestMoveOutDateFirstSummerResidents(Date latestMoveOutDateFirstSummerResidents) {
		this.latestMoveOutDateFirstSummerResidents = latestMoveOutDateFirstSummerResidents;
	}

	public Optional<Date> getEarliestMoveInDateSecondSummerResidents() {
		return Optional.ofNullable(earliestMoveInDateSecondSummerResidents);
	}

	public void setEarliestMoveInDateSecondSummerResidents(Date earliestMoveInDateSecondSummerResidents) {
		this.earliestMoveInDateSecondSummerResidents = earliestMoveInDateSecondSummerResidents;
	}

	public Optional<Date> getLatestMoveOutDateSecondSummerResidents() {
		return Optional.ofNullable(latestMoveOutDateSecondSummerResidents);
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
