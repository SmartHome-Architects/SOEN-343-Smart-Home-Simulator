package domain.house;


import domain.sensors.Door;
import domain.sensors.Light;
import domain.sensors.Window;

import java.util.ArrayList;
import java.util.List;

public class House {
    private List<Room> rooms = new ArrayList<>();

    public House(){
        this.rooms = Layout.loadLayout();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

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

}
