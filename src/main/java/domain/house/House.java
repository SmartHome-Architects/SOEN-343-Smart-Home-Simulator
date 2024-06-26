package domain.house;


import domain.sensors.*;

import java.util.ArrayList;
import java.util.List;

//The class serves as a model for a house containing rooms and various sensors such as doors, windows, and lights.
public class House {
    private List<Zone> savedZones = new ArrayList<>(); // New list to store saved zones
    private List<Room> rooms = new ArrayList<>();

    public House(){
        this.rooms = Layout.loadLayout();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }
//It contains private fields to store saved zones and a list of rooms.
    public List<Room> getRooms() {
        return rooms;
    }

    public List<String> getRoomNames(){
        List<String> roomNames = new ArrayList<>();
        for(Room r:rooms){
            roomNames.add(r.getRoomName());
        }
        return roomNames;
    }

    public List<Door> getDoors(){
        List<Door> doors = new ArrayList<>();
        for (Room room: rooms) {
            if(room.getDoor() != null){
                doors.add(room.getDoor());
            }
        }

        return doors;
    }

    public List<Window> getWindows(){
        List<Window> windows = new ArrayList<>();
        for(Room room: rooms){
            if(room.getWindows() != null){
                for (Window window: room.getWindows()) {
                    windows.add(window);
                }
            }
        }
        return windows;
    }

    public List<Light> getLights(){
        List<Light> lights = new ArrayList<>();
        for(Room room: rooms){
            if(room.getLights() != null){
                for (Light light: room.getLights()) {
                    lights.add(light);
                }
            }
        }
        return lights;
    }

    public List<TempControlUnit> getUnits(){
        List<TempControlUnit> tempUnits = new ArrayList<>();
        for(Room room:rooms){
            tempUnits.add(room.getAcUnit());
            tempUnits.add(room.getHeater());
        }
        return tempUnits;
    }

    public List<Zone> getSavedZones() {
        return savedZones;
    }

}
