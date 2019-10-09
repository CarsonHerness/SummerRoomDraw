package application;

public enum Dorm {
	DRINKWARD("Drinkward", "DW"),
	CASE("Case", "CA"),
	LINDE("Linde", "LI"),
	SONTAG("Sontag", "SG"),
	ATWOOD("Atwood", "AT"),
	SOUTH("South", "SO"),
	NORTH("North", "NO"),
	EAST("East", "EA"),
	WEST("West", "WE");
	
	private final String name;
	private final String abbreviation;
	
	Dorm(String name, String abbreviation) {
		this.name = name;
		this.abbreviation = abbreviation;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public String toStringAbbreviation() {
		return abbreviation;
	}
}
