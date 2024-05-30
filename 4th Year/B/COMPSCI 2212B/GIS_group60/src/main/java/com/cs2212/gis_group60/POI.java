package com.cs2212.gis_group60;
import java.util.*;

public class POI {
	int ID;
	String type;
	Building building;
	Floor floor;
	Room room;
	String description;
	int highlight;
	boolean isFavorite;
	public POI(String type, Building building, Floor floor, Room room, String description){
		this.type = type;
		this.building = building;
		this.floor = floor;
		this.room = room;
		this.description = description;	
		this.highlight = 0;
		this.isFavorite = false;
		this.ID = POIlist.POInum;
	}

	
	private void POIHighlight() {
		this.highlight = 1;
	}
	
	public void setFavorite() {
		this.isFavorite = true;
		FavPOIlist.addPOI(new POI(type, building, floor, room, description)); //�����ô��ӵ�list
	}


	

}


