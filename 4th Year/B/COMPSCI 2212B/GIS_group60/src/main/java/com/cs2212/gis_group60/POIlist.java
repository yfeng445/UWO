package com.cs2212.gis_group60;
import java.util.*;
public class POIlist {
	
	static ArrayList<POI> POIlist;
	static int POInum = POIlist.size();
	public POIlist() {
		this.POIlist = new ArrayList<POI>();
	}
	
	public static void addPOI(POI newPOI) {
		POIlist.add(newPOI);
		POInum++;
	}

	private static boolean validID(int id){
		if(id>POInum){
			return false;
		}
		else{
			if(POIlist.get(id)==null){
				return false;
			}
			else return true;
		}
	}

	/*
	return the POI object; null if the POI does not exist
	 */
	public static POI getPOI(int id) {
		if (!validID(id)){
			System.out.println("Invalid ID");
			return null;
		}
		else{
			return POIlist.get(id);
		}
	}

	/*
	A re-index function, it could remove all null POIs in list
	This function is disabled since id of each POI may change after reindex.
	 */
	private void reIndex(){
		for(int i = 0; i<POIlist.size(); i++){
			if(!(POIlist.get(i)==null)){
				POIlist.get(i).ID = i;
			}
		}
	}

	/*
	Set element with given id to null;
	Do nothing if id is invalid
	 */
	public static void removePOI(int id) {
		if(!validID(id)){
			System.out.println("Invalid ID");
		}
		else{
			POIlist.set(id, null);
		}
	}

	/*
	Adjust POI; putting a new POI on specific id
	 */
	public static void setPOI(int id, String type, Building building, Floor floor, Room room, String description) {
		if(!validID(id)){
			System.out.println("Invalid ID");
		}
		else{
			POI newPOI = new POI(type, building, floor, room, description);
			POIlist.set(id, newPOI);
		}
		
	}
}
