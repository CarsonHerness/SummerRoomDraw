package application;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class PersonTest {

	// June 5, 2019 00:00:00 in ms
	public static final Date summerMathEnd = new Date(1591315200000L);
	
	public static Room summerMathRoom = new Room.Builder(Dorm.ATWOOD, "111", 3).build();

	public static Person summerMathOnly = new Person.Builder("Summer", "Math", "01", ClassYear.YEAR_2022, true, true)
			.springMoveOutDate(summerMathEnd).springRoom(summerMathRoom).build();

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	// Person constructor test: set all values and check each with getters
	
	
	// Maximal roommates added firstSummer, check room sizes along the way
	
	// Maximal roommates added secondSummer

}
