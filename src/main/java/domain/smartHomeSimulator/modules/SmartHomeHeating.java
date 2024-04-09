package domain.smartHomeSimulator.modules;

import domain.house.Room;
import domain.house.Zone;
import domain.user.Users;

import javax.swing.*;
import java.util.Timer;
import java.util.*;

public class SmartHomeHeating implements Observable{
    private Users user;
    List<Zone> zones = new ArrayList<>();
    Map<Room, JLabel> temperatureLabels = new HashMap<>();

    private double outsideTemp;

    private boolean isActive;
    private Timer timer;

    private double tempRate = 0.05;

    public SmartHomeHeating(Map<Room,JLabel> temperatureLabels){
        //this.user = user;
        this.isActive = false;
        this.timer = new Timer();
        this.temperatureLabels = temperatureLabels;
        startTimer(); //start HVAC update timer
    }

    public double getOutsideTemp() {
        return outsideTemp;
    }

    public void setOutsideTemp(double outsideTemp) {
        this.outsideTemp = outsideTemp;
    }
    private void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timer.scheduleAtFixedRate(new TemperatureUpdateTask(), 0, 1000); //update every second if simulation speed set to default
            }
        }, 2000);
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

    public void setActive(boolean active) {
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
            z.update(tempRate,isActive(),outsideTemp,temperatureLabels);
        }
    }

    private class TemperatureUpdateTask extends TimerTask{
        @Override
        public void run() {
            notifyObservers();
        }
    }

    public List<Zone> getZones() {
        return zones;
    }
}
