package domain.house;


import domain.sensors.Door;

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

    public List<Door> getDoors(){
        List<Door> doors = new ArrayList<>();
        for (Room room: rooms) {
            if(room.getDoor() != null){
                doors.add(room.getDoor());
            }
        }

        return doors;
    }

}
