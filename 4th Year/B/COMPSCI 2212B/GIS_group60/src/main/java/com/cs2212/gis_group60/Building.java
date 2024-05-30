package com.cs2212.gis_group60;
import java.util.ArrayList;

public class Building {
	private String buildingName;
	private ArrayList<Floor> floors;
	private ArrayList<Room> rooms;
	
	public Building(String name, ArrayList<Floor> floor, ArrayList<Room> room) {
		buildingName = name;
		floors = floor;
		rooms = room;
	}
	//Lots of sets
	public void setBuildingName(String name) {
		buildingName = name;
	}
	public void setFloor(ArrayList<Floor> floor) {
		floors = floor;
	}
	public void setRoom(ArrayList<Room> room) {
		rooms = room;
	}
	//Lots of gets
	public String getBuildingName() {
		return buildingName;
	}
	public ArrayList<Floor> getFloor(){
		return floors;
	}
	public ArrayList<Room> getRoom(){
		return rooms;
	}
	//Lots of adds
	public void addRoom(Room RM) {
		rooms.add(RM);
	}
	public void addFloor(Floor FL) {
		floors.add(FL);
	}
}
