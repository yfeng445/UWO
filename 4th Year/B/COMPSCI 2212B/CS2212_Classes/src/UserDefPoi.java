import java.util.*;

public class UserDefPoi extends POI{
	String type;
	String building;
	String floor;
	String room;
	String description;
	public UserDefPoi(String type, String building, String floor, String room, String description) {
		super(type, building, floor, room, description);
	}	
}
