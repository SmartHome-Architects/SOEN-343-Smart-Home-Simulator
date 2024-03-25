package domain.house;

import domain.sensors.TempControlUnit;
import domain.sensors.Window;

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

    public List<Room> getZoneRooms() {
        return zoneRooms;
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
    public void update(double tempRate, boolean isActive, double outsideTemp) {

        double temp_outside = outsideTemp;

        for (Room room: zoneRooms) {
            double roomTemp = room.getTemperature();
            if(!isActive){ // SHH is off. Room temp adjusts itself until temp outside is approximately equal to room temp.
                if(Math.floor(temp_outside) < Math.floor(roomTemp)){
                    room.setTemperature(roomTemp - (roomTemp * tempRate));
                }
                else if(Math.floor(temp_outside) > Math.floor(roomTemp)){
                    room.setTemperature(roomTemp + (roomTemp * tempRate));
                }
            }
            else{ // SHH is on.
                double upperThreshold = desiredZoneTemperature + 0.25;
                double lowerThreshold = desiredZoneTemperature - 0.25;

                if(roomTemp >= lowerThreshold && roomTemp <= upperThreshold){ // HVAC is paused if room temp is between thresholds.
                    for(Window w : room.getWindows()){
                        w.setOpen(false);
                    }
                    room.getAcUnit().turnOff();
                    return;
                }
                else if(temp_outside < roomTemp && temp_outside < desiredZoneTemperature){
                    room.getAcUnit().turnOff();
                    for (Window w : room.getWindows()) {
                        if(!w.isBlocked()){
                            w.setOpen(true);
                        }
                        else{
                            System.out.println("Window is blocked. Unable to close: " + w.getLocation() + w.getName() + w.getWindowID());
                        }
                    }
                    room.setTemperature(roomTemp - (roomTemp * tempRate));
                }
                else if(temp_outside > roomTemp && roomTemp > desiredZoneTemperature){
                    room.getAcUnit().turnOn();
                    room.setTemperature(roomTemp - (roomTemp * tempRate));
                }
            }
        }
    }
}
