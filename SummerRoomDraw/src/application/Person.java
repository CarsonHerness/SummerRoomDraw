package application;

import java.util.ArrayList;
import java.util.Date;

public class Person {
	String legalFirstName;
	String legalMiddleName;
	String legalLastName;
	String preferredName;
	String email;
	
	// For Claremont students, this is their Claremont ID. Otherwise, we generate their ID.
	String id;
	ClassYear classYear;
	boolean isHMCStudent;
	boolean isClaremontStudent;
	
	Date activityStartDate;
	Date activityEndDate;
	Date housingStartDate;
	Date housingEndDate;
	
	ArrayList<Person> firstRoommates;
	ArrayList<Person> secondRoomates;
	
	Room springRoom;
	Room fallRoom;
	Room firstSummerRoom;
	Room secondSummerRoom;
	
	boolean summerMathStudent;
}
