package com.cs2212.gis_group60;

import java.util.ArrayList;

public class Driver_1 {
    public Driver_1(){
    }
    public static void main(String[] args){
        Room RM0 = new Room();
        Room RM1 = new Room();

        //dummy cases
        RM0.setRoomName("Room 0");
        RM0.setRoomDetail("Room 0 Detail");
        RM0.setRoomPosition(0,0);
        RM0.setRoomType("Type 0");
        RM1.setRoomName("Room 1");
        RM1.setRoomDetail("Room 1 Detail");
        RM1.setRoomPosition(1,1);
        RM1.setRoomType("Type 1");


        Building BU = new Building();
        BU.addRoom(RM0);
        BU.addRoom(RM1);
        System.out.println("----");
        System.out.println(BU.getRoom().get(0).getRoomName());
        System.out.println(BU.getRoom().get(0).getRoomType());
        System.out.println(BU.getRoom().get(0).getRoomDetail());
        System.out.println(BU.getRoom().get(1).getRoomName());
        System.out.println(BU.getRoom().get(1).getRoomType());
        System.out.println(BU.getRoom().get(1).getRoomDetail());

        System.out.println("--------------------------");
        ArrayList<Room> newRoom = new ArrayList<>();
        BU.setRoom(newRoom);
        System.out.println(BU.getRoom().get(0).getRoomName());
        System.out.println(BU.getRoom().get(0).getRoomType());
        System.out.println(BU.getRoom().get(0).getRoomDetail());
    }
}
