package application;

import java.util.HashMap;
import java.util.Map;

public class School {
	
	private static Map<String, Room> dormRooms = new HashMap<>();
	private static Map<String, Person> students = new HashMap<>();

	// function to read from file and fill the dormRooms
	private static void readDormRoomsFromFile() {
		
	}
	
	public static void fillSchoolInformation() {
		readDormRoomsFromFile();
		// possibly do something about initializing student data here
	}
	
	public static Room getRoom(String roomName) {
		return dormRooms.get(roomName);
	}
	
	public static Person getPerson(String id) {
		return students.get(id);
	}
}
