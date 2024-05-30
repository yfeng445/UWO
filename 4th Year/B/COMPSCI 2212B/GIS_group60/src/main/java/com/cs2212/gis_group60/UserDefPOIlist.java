package com.cs2212.gis_group60;
import java.util.*;

public class UserDefPOIlist extends POIlist{
	
	static ArrayList<POI> UserDefPOIlist;
	
	public UserDefPOIlist() {
		super();
		this.UserDefPOIlist = new ArrayList<POI>();
	}
	
	
	//User define interface
	public static void addPOI(POI newPOI) {
		POIlist.add(newPOI);
		UserDefPOIlist.add(newPOI);
		POInum++;
	}
	
	
}
