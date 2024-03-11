package domain.house;

import domain.sensors.*;

import java.util.List;

public class Room {
    private int id;
    private String roomName;
    private Door door;
    private List<Light> lights;
    private List<Window> windows;
    private AC acUnit;
    private Heater heater;
    private double temperature;

    public Room(int id, String roomName, Door door, List<Light> lights, List<Window> windows,
                AC acUnit, Heater heater, double temperature) {
        this.id = id;
        this.roomName = roomName;
        this.door = door;
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

    public Door getDoor() {
        return door;
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

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
