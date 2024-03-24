package domain.smartHomeSimulator.modules;

import domain.house.Zone;
import domain.user.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SmartHomeHeating implements Observable{
    private Profile user;
    List<Zone> zones = new ArrayList<>();

    private boolean isActive;
    private Timer timer;

    private double tempRate = 0.05;

    public SmartHomeHeating(Profile user){
        this.user = user;
        this.isActive = false;
        startTimer(); //start HVAC update timer
    }

    private void startTimer() {
            timer.scheduleAtFixedRate(new TemperatureUpdateTask(), 0, 1000); // update every second
    }

    public void attach(Zone zone){
        //check permission here
        zones.add(zone);
    }

    public void detach(Zone zone){
        //check permission
        zones.remove(zone);
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
            if(isActive()){
                z.update(tempRate);
            }
        }
    }

    private class TemperatureUpdateTask extends TimerTask{
        @Override
        public void run() {
            notifyObservers();
        }
    }
}
