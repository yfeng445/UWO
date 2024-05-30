package com.cs2212.gis_group60;

public class Driver_2 {
    public Driver_2(){
    }
    public static void main(String[] args){

        Floor FL0 = new Floor();
        Floor FL1 = new Floor();

        //dummy cases
        FL0.setFloorName("FL0 Name");
        FL1.setFloorName("FL1 Name");

        Building BU = new Building();
        BU.addFloor(FL0);
        BU.addFloor(FL1);
        System.out.println("----");
        System.out.println(BU.getFloor().get(0).getFloorName());
        System.out.println(BU.getFloor().get(1).getFloorName());


    }
}
