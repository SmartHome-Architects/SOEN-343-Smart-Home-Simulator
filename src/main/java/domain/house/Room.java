package src.main.java.domain.house;

import src.main.java.domain.sensors.*;

import java.util.List;

public class Room {
    private int id;
    private String roomName;
    private List<Door> doors;
    private List<Light> lights;
    private List<Window> windows;
    private AC acUnit;
    private Heater heater;
    private double temperature;

    public Room(int id,String roomName, List<Door> doors, List<Light> lights, List<Window> windows,
                AC acUnit, Heater heater, double temperature){
        this.id = id;
        this.roomName = roomName;
        this.doors= doors;
        this.lights = lights;
        this.windows = windows;
        this.acUnit = acUnit;
        this.heater = heater;
        this.temperature = temperature;
    }

    public int getId() {
        return id;
    }

    public String getRoomName() {
        return roomName;
    }

    public List<Door> getDoors() {
        return doors;
    }

    public List<Light> getLights() {
        return lights;
    }

    public List<Window> getWindows() {
        return windows;
    }

    public AC getAcUnit() {
        return acUnit;
    }

    public Heater getHeater() {
        return heater;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature){
        this.temperature = temperature;
    }
}
