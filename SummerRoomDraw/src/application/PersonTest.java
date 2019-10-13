package application;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class PersonTest {

	// May 18, 2019 00:00:00 in ms
	public static final Date date1 = new Date(1589760000000L);

	// May 24, 2019 00:00:00 in ms
	public static final Date date2 = new Date(1590278400000L);

	// May 25, 2019 00:00:00 in ms
	public static final Date date3 = new Date(1590364800000L);

	// June 5, 2019 00:00:00 in ms
	public static final Date date4 = new Date(1591315200000L);

	// July 7, 2019 00:00:00 in ms
	public static final Date date5 = new Date(1594080000000L);

	// August 7, 2019 00:00:00 in ms
	public static final Date date6 = new Date(1596758400000L);

	// August 8, 2019 00:00:00 in ms
	public static final Date date7 = new Date(1596844800000L);

	// defaulted to normal values for HMC student
	// Monday, 18 May 2020 0:00:00 GMT
	private static final Date springMoveOutDate = new Date(1589760000000L);

	// Sunday, 30 August 2020 0:00:00 GMT
	private static final Date fallMoveInDate = new Date(1598745600000L);

	public static Room summerMathRoom = new Room.Builder(Dorm.ATWOOD, "111", 3).build();
	public static Room room1 = new Room.Builder(Dorm.DRINKWARD, "121", 3).build();
	public static Room room2 = new Room.Builder(Dorm.ATWOOD, "331", 1).build();
	public static Room room3 = new Room.Builder(Dorm.SONTAG, "214G", 2).build();
	public static Room room4 = new Room.Builder(Dorm.DRINKWARD, "113", 1).build();

	public static final String email = "person1@gmail.com";

	public static Person summerMathOnly = new Person.Builder("Summer", "Math", "00", ClassYear.YEAR_2023)
			.springMoveOutDate(date3).springRoom(summerMathRoom).build();

	// uses default values
	public static Person person1 = new Person.Builder("Person1First", "Person1Last", "01", ClassYear.YEAR_2021)
			.activityStartDate(date1).activityEndDate(date6).fallRoom(room1).springRoom(room3)
			.firstHousingStartDate(date1).firstHousingEndDate(date4).secondHousingStartDate(date5)
			.secondHousingEndDate(date7).preferredName("Person1Preferrred").fallSquat(true).build();

	// overrides default values
	public static Person person2 = new Person.Builder("Person2First", "Person2Last", "02", ClassYear.YEAR_2020)
			.springMoveOutDate(date2).fallMoveInDate(date7).legalMiddleName("Person2Middle").email(email)
			.isClaremontStudent(false).isHMCStudent(false).build();

	public static Person person3 = new Person.Builder("Person3First", "Person3Last", "03", ClassYear.YEAR_2023).build();

	@Test
	public void constructorChecks() {
		assertEquals(person1.getLegalFirstName(), "Person1First");
		assertEquals(person1.getLegalLastName(), "Person1Last");
		assertEquals(person1.getLegalMiddleName(), null);
		assertEquals(person1.getPreferredName(), "Person1Preferred");
		assertEquals(person1.getId(), "01");
		assertEquals(person1.getClassYear(), ClassYear.YEAR_2021);
		assertEquals(person1.getActivityStartDate(), date1);
		assertEquals(person1.getActivityEndDate(), date6);
		assertEquals(person1.getFallRoom(), room1);
		assertEquals(person1.getSpringRoom(), room3);
		assertEquals(person1.getFirstHousingStartDate(), date1);
		assertEquals(person1.getFirstHousingEndDate(), date4);
		assertEquals(person1.getEmail(), null);
		assertEquals(person1.getIsHMCStudent(), true);
		assertEquals(person1.getIsClaremontStudent(), true);
		assertEquals(person1.getSpringMoveOutDate(), springMoveOutDate);
		assertEquals(person1.getFallMoveInDate(), fallMoveInDate);
		assertEquals(person1.getSpringRoom(), room3);
		assertEquals(person1.getFallRoom(), room1);
		assertEquals(person1.getSpringSquat(), false);
		assertEquals(person1.getFallSquat(), true);
		assertEquals(person1.getSecondHousingStartDate(), date5);
		assertEquals(person1.getSecondHousingEndDate(), date7);
		assertEquals(person1.getPrePlaced(), true);
		
		assertEquals(person2.getLegalMiddleName(), "Person2Middle");
		assertEquals(person2.getPreferredName(), null);
		assertEquals(person2.getActivityStartDate(), null);
		assertEquals(person2.getActivityEndDate(), null);
		assertEquals(person2.getFallRoom(), null);
		assertEquals(person2.getSpringRoom(), null);
		assertEquals(person2.getFallMoveInDate(), date7);
		assertEquals(person2.getSpringMoveOutDate(), date2);
		assertEquals(person2.getEmail(), email);
		assertEquals(person2.getIsHMCStudent(), false);
		assertEquals(person2.getIsClaremontStudent(), false);
		assertEquals(person2.getPrePlaced(), true);
		
		assertEquals(person3.getPrePlaced(), false);
	}

	// Maximal roommates added firstSummer, check room sizes along the way

	// Maximal roommates added secondSummer

}
