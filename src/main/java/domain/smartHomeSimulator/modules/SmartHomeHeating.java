package domain.smartHomeSimulator.modules;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.house.House;
import domain.house.Room;
import domain.house.Zone;
import domain.user.Users;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

public class SmartHomeHeating implements Observable{
    private Users user;
    List<Zone> zones = new ArrayList<>();
    Map<Room, JLabel> temperatureLabels = new HashMap<>();

    private double outsideTemp;

    private boolean isActive;
    private Timer timer;

    private boolean paused = true;

    private double tempRate = 0.05;

    private SmartHomeSecurity shp;

    public SmartHomeHeating(Map<Room,JLabel> temperatureLabels, SmartHomeSecurity shp){
        //this.user = user;
        this.isActive = false;
        this.timer = new Timer();
        this.temperatureLabels = temperatureLabels;
        this.shp = shp;
    }

    public double getOutsideTemp() {
        return outsideTemp;
    }

    public void setOutsideTemp(double outsideTemp) {
        this.outsideTemp = outsideTemp;
    }
    public void startTimer(int speed){
        timer.cancel();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                    timer.scheduleAtFixedRate(new TemperatureUpdateTask(), 0, 1000/speed); //update every second if simulation speed set to default
            }
        }, 2000);
    }

    public void pauseTimer(){
        paused = true;
    }

    public boolean isPaused() {
        return paused;
    }

    public void resumeTimer(int speedMultiplier){
        paused = false;
        startTimer(speedMultiplier);
    }

    public void attach(Zone zone){
        //check permission here
        zones.add(zone);
    }

    public void detach(Zone zone){
        //check permission
        zones.remove(zone);
        for(Room room: zone.getZoneRooms()){
            room.getAcUnit().turnOff();
            room.getHeater().turnOff();
        }
    }

    public void setZoneTemperature(String zoneName, double zoneTemp){
        Zone z = getZone(zoneName);
        if(z != null){
            z.setDesiredZoneTemperature(zoneTemp);
        }
        else{
            System.out.println("Zone does not exist.");
        }
    }

    private Zone getZone(String name){
        for (Zone zone: zones) {
            zone.getZoneName().equals(name);
            return zone;
        }
        return null;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active){
        isActive = active;
        if(isActive()){
            this.tempRate = 0.1; // temp rate when SHH active.
        }
        else{
            this.tempRate = 0.05; // default temp rate when SHH is not(!) active.
        }

    }

    public void notifyObservers(){
        for (Zone z: zones) {
            z.update(tempRate,isActive(),outsideTemp,temperatureLabels,shp.isAwayModeActive());
        }
    }

    private class TemperatureUpdateTask extends TimerTask{
        @Override
        public void run(){
            if(!paused){
                notifyObservers();
            }
        }
    }

    public List<Zone> getZones(){
        return zones;
    }

    public void loadZones(House h) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        String file = "database/zones.json";
        List<Map<String, Object>> zoneList = mapper.readValue(new File(file), new TypeReference<List<Map<String, Object>>>() {});
        for (Map<String, Object> zoneMap : zoneList){
            String zoneName = (String) zoneMap.get("zoneName");
            double desiredTemperature = (double) zoneMap.get("desiredTemperature");
            Zone zone = new Zone(zoneName,desiredTemperature);
            List<Map<String, Object>> roomList = (List<Map<String, Object>>) zoneMap.get("rooms");
            for (Map<String, Object> roomMap : roomList){
                String roomName = roomMap.get("roomName").toString();
                List<Room> rooms = h.getRooms();
                for (Room r:rooms){
                    if(r.getRoomName().equals(roomName)){
                        zone.addRoomToZone(r);
                    }
                }
            }
            this.attach(zone);
        }
    }
}
