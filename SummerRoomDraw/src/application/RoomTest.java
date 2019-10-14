package application;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.junit.Test;

public class RoomTest {

	// May 20, 2019 00:00:00 in ms
	public static final Date date1 = new Date(1589932800000L);

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

	// Monday, 18 May 2020 0:00:00 GMT
	private static final Date springMoveOutDate = new Date(1589760000000L);

	// Sunday, 30 August 2020 0:00:00 GMT
	private static final Date fallMoveInDate = new Date(1598745600000L);

	Room room1 = new Room.Builder(Dorm.DRINKWARD, "111E", 3).build();
	Room room2 = new Room.Builder(Dorm.CASE, "111", 1).build();
	Room room3 = new Room.Builder(Dorm.SONTAG, "113D", 2).build();

	Person person1 = new Person.Builder("P1", "P1", "01", ClassYear.YEAR_2020).springMoveOutDate(date2)
			.firstHousingStartDate(date3).firstHousingEndDate(date7).build();
	Person person2 = new Person.Builder("P2", "P2", "02", ClassYear.YEAR_2021).firstHousingStartDate(date1)
			.firstHousingEndDate(date6).fallMoveInDate(date7).build();
	Person person3 = new Person.Builder("P3", "P3", "03", ClassYear.YEAR_2023).springMoveOutDate(date1).build();
	Person person4 = new Person.Builder("P4", "P4", "04", ClassYear.YEAR_2022).springMoveOutDate(date1)
			.firstHousingEndDate(date2).firstHousingEndDate(date4).build();

	@Test
	public void constructorTest() {
		assertEquals(room1.getDorm(), Dorm.DRINKWARD);
		assertEquals(room1.getRoomName(), "DW111E");
		assertEquals(room1.getRoomNumber(), "111E");
		assertEquals(room1.getMaxCapacity(), 3);
		assertEquals(room1.getMinCapacity(), 2);
		assertEquals(room1.getFallResidentMoveInDate(), fallMoveInDate);
		assertEquals(room1.getSpringResidentMoveOutDate(), springMoveOutDate);
		assertFalse(room1.getEarliestMoveInDateFirstSummerResidents().isPresent());
		assertFalse(room1.getEarliestMoveInDateSecondSummerResidents().isPresent());
		assertFalse(room1.getLatestMoveOutDateFirstSummerResidents().isPresent());
		assertFalse(room1.getLatestMoveOutDateSecondSummerResidents().isPresent());
	}

	@Test
	public void addSpringResidentTest() {
		assertEquals(room1.getSpringResidentMoveOutDate(), springMoveOutDate);

		room1.addSpringResident(person1);
		assertEquals(room1.getSpringResidentMoveOutDate(), date2);

		room1.addSpringResident(person2);
		room1.addSpringResident(person3);

		assertEquals(room1.getSpringResidentMoveOutDate(), date2);

		try {
			room1.addSpringResident(person4);
			fail("Should not have allowed addition of 4th person");
		} catch (Exception e) {
			// do nothing: expected
		}

		ArrayList<Person> expectedSpringResidents = new ArrayList<>();
		expectedSpringResidents.add(person1);
		expectedSpringResidents.add(person2);
		expectedSpringResidents.add(person3);

		Collections.sort(expectedSpringResidents);

		ArrayList<Person> actualSpringResidents = room1.getSpringResidents();
		Collections.sort(actualSpringResidents);

		assertEquals(expectedSpringResidents, actualSpringResidents);
	}

	@Test
	public void addFallResidentTest() {
		assertEquals(room3.getFallResidentMoveInDate(), fallMoveInDate);

		room3.addFallResident(person2);
		assertEquals(room3.getFallResidentMoveInDate(), date7);

		room3.addFallResident(person3);
		assertEquals(room3.getFallResidentMoveInDate(), date7);

		try {
			room3.addFallResident(person4);
			fail("Should not have been allowed to add third person");
		} catch (Exception e) {
			// do nothing: expected
		}

		ArrayList<Person> expectedFallResidents = new ArrayList<>();
		expectedFallResidents.add(person2);
		expectedFallResidents.add(person3);

		Collections.sort(expectedFallResidents);

		ArrayList<Person> actualFallResidents = room3.getFallResidents();
		Collections.sort(actualFallResidents);

		assertEquals(expectedFallResidents, actualFallResidents);
	}

	@Test
	public void addAndRemoveFirstSummerResidentTest() {
		// check move in and move out dates
		assertFalse(room3.getEarliestMoveInDateFirstSummerResidents().isPresent());
		
		room3.addFirstSummerResident(person1);
		assertEquals(room3.getEarliestMoveInDateFirstSummerResidents(), date3);
		assertEquals(room3.getLatestMoveOutDateFirstSummerResidents(), date7);
		
		room3.addFallResident(person2);
		assertEquals(room3.getEarliestMoveInDateFirstSummerResidents(), date1);
		assertEquals(room3.getLatestMoveOutDateFirstSummerResidents(), date7);
		
		try {
			room3.addFirstSummerResident(person4);
			fail("Should not have been allowed to add third person");
		} catch (Exception e) {
			// do nothing: expected
		}
		
		room3.removeFirstSummerResident(person1);
		assertEquals(room3.getEarliestMoveInDateFirstSummerResidents(), date1);
		assertEquals(room3.getLatestMoveOutDateFirstSummerResidents(), date6);
		
		try {
			room3.addFirstSummerResident(person3);
			fail("Should not have been able to add a resident without First Summer dates");
		} catch (Exception e) {
			// do nothing: expected
		}
		
		room3.addFirstSummerResident(person4);
		assertEquals(room3.getEarliestMoveInDateFirstSummerResidents(), date1);
		assertEquals(room3.getLatestMoveOutDateFirstSummerResidents(), date6);
		
		ArrayList<Person> expectedFirstSummerResidents = new ArrayList<>();
		expectedFirstSummerResidents.add(person2);
		expectedFirstSummerResidents.add(person4);
		
		Collections.sort(expectedFirstSummerResidents);
		
		ArrayList<Person> actualFirstSummerResidents = room3.getFirstSummerResidents();
		Collections.sort(actualFirstSummerResidents);
		
		assertEquals(expectedFirstSummerResidents, actualFirstSummerResidents);
		
		// check roommates
		assertTrue(person1.getFirstRoommates().isEmpty());
		assertEquals(person2.getFirstRoommates(), new ArrayList<Person>().add(person4));
		assertTrue(person3.getFirstRoommates().isEmpty());	
		assertEquals(person4.getFirstRoommates(), new ArrayList<Person>().add(person2));
	}

	@Test
	public void addAndRemoveSecondSummerResidentTest() {
		// check move in and move out dates

		// TODO: check roommates
	}
}
