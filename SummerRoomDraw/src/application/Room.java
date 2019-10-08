package application;

import java.util.ArrayList;
import java.util.Date;

public class Room {
	Dorm dorm;
	String roomNumber;
	
	FallRoomType fallRoomType;
	
	ArrayList<Person> springResidents;
	ArrayList<Person> firstSummerResidents;
	ArrayList<Person> secondSummerResidents;
	ArrayList<Person> fallResidents;
	
	int minCapacity;
	int maxCapacity;
	
	// Spring resident move in date
	Date firstAvailableDate;
	Date firstCloseDate;
	
	
	Date secondAvailableDate;
	Date secondCloseDate;
	
	boolean summerMathRoom;
}
