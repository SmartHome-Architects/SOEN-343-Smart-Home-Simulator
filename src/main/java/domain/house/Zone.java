package domain.house;

import domain.sensors.TempControlUnit;

import java.util.ArrayList;
import java.util.List;

public class Zone implements TempObserver{
    List<Room> zoneRooms = new ArrayList<>();
    private double zoneTemperature;

    public Zone(double zoneTemperature){
        this.zoneTemperature = zoneTemperature;
    }

    public void addRoomToZone(Room room){
        zoneRooms.add(room);
    }

    public void removeRoomFromZone(Room room){
        zoneRooms.remove(room);
    }

    @Override
    public void update(TempControlUnit unit) {
        for (Room room: zoneRooms) {
            room.setTemperature(zoneTemperature);
        }
    }
}
