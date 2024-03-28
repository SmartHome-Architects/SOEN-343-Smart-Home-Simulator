package domain.house;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.sensors.TempControlUnit;
import domain.sensors.Window;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Zone implements Observer {
    List<Room> zoneRooms = new ArrayList<>();

    private String zoneName;
    private double zoneTempThreshold = 20.0;
    private double desiredZoneTemperature;

    public Zone(String zoneName, double desiredZoneTemperature){
        this.zoneName = zoneName;
        this.desiredZoneTemperature = desiredZoneTemperature;
    }

    public Zone(String zoneName){
        this.zoneName = zoneName;
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
    public void update(double tempRate, boolean isActive, double outsideTemp, Map<Room, JLabel> temperatureLabels) {

        double temp_outside = outsideTemp;

        try{
            desiredZoneTemperature = readZoneDesiredTemp();
        }catch (IOException e){
            System.out.println(e);
        }


        for (Room room: zoneRooms) {
            double roomTemp = room.getTemperature();
            if(!isActive){ // SHH is off. Room temp adjusts itself until temp outside is approximately equal to room temp.
                if(Math.floor(temp_outside) < Math.floor(roomTemp)){
                    room.setTemperature(Math.floor(roomTemp - (roomTemp * tempRate)));
                }
                else if(Math.floor(temp_outside) > Math.floor(roomTemp)){
                    room.setTemperature(Math.floor(roomTemp + (roomTemp * tempRate)));
                }
            }
            else{ // SHH is on.
                double upperThreshold = desiredZoneTemperature + 0.25;
                double lowerThreshold = desiredZoneTemperature - 0.25;

                if(lowerThreshold <= roomTemp && roomTemp <= upperThreshold){ // HVAC is paused if room temp is between thresholds.
                    for(Window w : room.getWindows()){
                        w.setOpen(false);
                    }
                    room.getAcUnit().turnOff();
                    room.getHeater().turnOff();

                }
                else if(roomTemp <= desiredZoneTemperature){ // turn heating on until
                    room.getHeater().turnOn();
                    double temp = roomTemp + (roomTemp * tempRate);
                    if(Math.floor(temp) > desiredZoneTemperature){
                        temp = temp - 1;
                    }
                    room.setTemperature(Math.floor(temp));
                }

//                else if(temp_outside < roomTemp && temp_outside < desiredZoneTemperature){
//                    room.getAcUnit().turnOff();
//                    for (Window w : room.getWindows()) {
//                        if(!w.isBlocked()){
//                            w.setOpen(true);
//                        }
//                        else{
//                            System.out.println("Window is blocked. Unable to close: " + w.getLocation() + w.getName() + w.getWindowID());
//                        }
//                    }
//                    room.setTemperature(roomTemp - (roomTemp * tempRate));
//                }
                else if(temp_outside > roomTemp && roomTemp > desiredZoneTemperature){
                    room.getAcUnit().turnOn();
                    room.setTemperature(Math.floor(roomTemp - (roomTemp * tempRate)));
                }
            }
            JLabel label = temperatureLabels.get(room);
            String labelText = Double.toString(room.getTemperature()) + "°";
            label.setText(labelText);
        }
    }

    private double readZoneDesiredTemp() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String file = "database/zones.json";
        double temp = 0;
        List<Map<String, Object>> zoneList = mapper.readValue(new File(file), new TypeReference<List<Map<String, Object>>>() {});
        for (Map<String, Object> zoneMap : zoneList) {
            String zoneName = (String) zoneMap.get("zoneName");
            if(zoneName.equals(this.zoneName)){
                temp = (double) zoneMap.get("desiredTemperature");
            }
        }
        return temp;
    }
}
