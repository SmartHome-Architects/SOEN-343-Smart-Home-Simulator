package domain.house;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.sensors.TempControlUnit;
import domain.sensors.Window;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
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
                room.getAcUnit().turnOff();
                room.getHeater().turnOff();
                if(temp_outside < roomTemp){
                    room.setTemperature(roomTemp - tempRate);
                }
                else if(temp_outside > roomTemp){
                   room.setTemperature(roomTemp + tempRate);
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

                    room.setTemperature(desiredZoneTemperature);
                }

                else if(roomTemp < desiredZoneTemperature){
                    if(temp_outside < desiredZoneTemperature){
                        room.turnHeatingOn();
                        for(Window w: room.getWindows()){
                            w.setOpen(false);
                        }
                    }
                    else if(temp_outside >= desiredZoneTemperature){
                        room.getHeater().turnOff();
                        for(Window w: room.getWindows()) {
                            w.setOpen(true);
                        }
                    }
                    double temp = roomTemp + tempRate;
                    room.setTemperature(temp);
                }

                else if(roomTemp > desiredZoneTemperature){
                    if(temp_outside >= desiredZoneTemperature){
                        room.turnACOn();
                        for(Window w: room.getWindows()){
                            w.setOpen(false);
                        }
                    }
                    else if(temp_outside < desiredZoneTemperature){
                        room.getAcUnit().turnOff();
                        for(Window w: room.getWindows()) {
                            w.setOpen(true);
                        }
                    }
                    double temp = roomTemp - tempRate;
                    room.setTemperature(temp);
                }
            }
            DecimalFormat df = new DecimalFormat("#.#");
            JLabel label = temperatureLabels.get(room);
            Double rTemp = room.getTemperature();
            String room_temp;

            double roundedTemp = Double.parseDouble(df.format(rTemp));
            int intVal = (int) roundedTemp;
            double decimalValue = roundedTemp - intVal;

            if(decimalValue < 0.25){
                roundedTemp = intVal;
            }
            else if(decimalValue < 0.75){
                roundedTemp = intVal + 0.5;
            }
            else{
                roundedTemp = intVal + 1;
            }

            room_temp = String.valueOf(roundedTemp);

            String labelText = room_temp + "°";
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
