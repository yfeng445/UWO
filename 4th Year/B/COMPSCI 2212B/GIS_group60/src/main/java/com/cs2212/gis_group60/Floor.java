package com.cs2212.gis_group60;
import java.util.ArrayList;

public class Floor{
        private String floorName;
        private ArrayList<Room> POI;
        private ArrayList<Room> dePOI;
        private ArrayList<Room> classroom;
        private ArrayList<Room> stairwell;
        private ArrayList<Room> lab;
        private ArrayList<Room> entrance;
        private ArrayList<Room> userFavourite;
        private ArrayList<Room> userDefined;
        
        public Floor(int floorNumber, String floorName, ArrayList<Room> POI) {
            this.floorName = floorName;
            this.POI = POI;
        }
        //Lots of sets

        public void setFloorName(String name) {
            floorName = name;
        }
        public void setFloorPOI(ArrayList<Room> POIs) {
            POI = POIs;
        }
               //assign the POIs
        public void assignPOI(){
            for (int i = 0; i < POI.size(); i++) {
                switch (POI.get(i).getRoomType()){
                    case "washroom":
                        dePOI.add(POI.get(i));
                        break;
                    case "elevator":
                        dePOI.add(POI.get(i));
                        break;
                    case "stairwell":
                        stairwell.add(POI.get(i));
                    case "lab":
                        lab.add(POI.get(i));
                        break;
                    case "entrance":
                        entrance.add(POI.get(i));
                        break;
                    case "userDefined":
                        userDefined.add(POI.get(i));
                        break;
                    case "classroom":
                        classroom.add(POI.get(i));
                    default:
                        break;
                }
                if (POI.get(i).getRoomFavourite() == 1){
                    userFavourite.add(POI.get(i));
                }
            }
        }
        //Lots of gets
        public String getFloorName() {
            return floorName;
        }
        
        public ArrayList<Room> getFloorPOI(){
            return POI;
        }
        
        public ArrayList<Room> getFloorDefaultPOI(){
            return dePOI;
        } 
        
        public ArrayList<Room> getFloorCreatedPOI(){
            return userDefined;
        }
        
        public ArrayList<Room> getFloorClassroom(){
            return classroom;
        }
        
        public ArrayList<Room> getFloorStair(){
            return stairwell;
        }
        
        public ArrayList<Room> getFloorLab(){
            return lab;
        }
        
        public ArrayList<Room> getFloorEntrance(){
            return entrance;
        }
        
        public ArrayList<Room> getFloorFavourite(){
            return userFavourite;
        }
        //Lots of adds
        public void addPOI(Room pois) {
            POI.add(pois);
        }
        
        public void addDefaultPOI(Room pois) {
            dePOI.add(pois);
        }
        
        public void addCreatedPOI(Room pois) {
            userDefined.add(pois);
        }
        
        public void addClassroom(Room pois) {
            classroom.add(pois);
        }
        
        public void addStair(Room pois) {
            stairwell.add(pois);
        }
        
        public void addLab(Room pois) {
            lab.add(pois);
        }
        
        public void addEntrance(Room pois) {
            entrance.add(pois);
        }
        
        public void addFavourite(Room pois) {
            userFavourite.add(pois);
        }
}