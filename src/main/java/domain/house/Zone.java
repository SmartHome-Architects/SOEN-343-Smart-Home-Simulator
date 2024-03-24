package domain.house;

import domain.sensors.TempControlUnit;

import java.util.ArrayList;
import java.util.List;

public class Zone implements Observer{
    List<Room> zoneRooms = new ArrayList<>();

    private String zoneName;
    private double zoneTempThreshold = 20.0;
    private double desiredZoneTemperature;

    public Zone(String zoneName, double desiredZoneTemperature){
        this.zoneName = zoneName;
        this.desiredZoneTemperature = desiredZoneTemperature;
    }

    public void addRoomToZone(Room room){
        zoneRooms.add(room);
    }

    public void removeRoomFromZone(Room room){
        zoneRooms.remove(room);
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setDesiredZoneTemperature(double temp){
        this.desiredZoneTemperature = temp;
    }

    public double getDesiredZoneTemperature() {
        return desiredZoneTemperature;
    }

    @Override
    public void update(double tempRate) {
        for (Room room: zoneRooms) {
            // zone temperature algorithm

            // getOutsideTemp
            // getSeason
            double roomTemp = room.getTemperature();

            // run checks and setTemperature accordingly.

            room.setTemperature(room.getTemperature() + (room.getTemperature() * tempRate)); // decrease temp
            room.setTemperature(room.getTemperature() + (room.getTemperature() * tempRate)); // increase temp
        }
    }
}
