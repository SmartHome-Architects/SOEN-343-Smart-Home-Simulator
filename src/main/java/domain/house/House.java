package domain.house;

import java.util.ArrayList;
import java.util.List;

public class House {
    private List<Room> rooms = new ArrayList<>();

    public House() {
        this.rooms = Layout.loadLayout();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
