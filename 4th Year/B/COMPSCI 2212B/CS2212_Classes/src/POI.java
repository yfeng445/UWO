import java.util.*;

public class POI {
	String type;
	String building;
	String floor;
	String room;
	String description;
	int highlight;
	int isFavorite;
	public POI(String type, String building, String floor, String room, String description){
		this.type = type;
		this.building = building;
		this.floor = floor;
		this.room = room;
		this.description = description;	
		this.highlight = 0;
		this.isFavorite = 0;
	}

	
	void POIHighlight() {
		this.highlight = 1;
	}
	
	void setFavorite() {
		this.isFavorite = 1;
		//FavPOIlist.addPOI(null); 这个怎么添加到list
	}
	

}


