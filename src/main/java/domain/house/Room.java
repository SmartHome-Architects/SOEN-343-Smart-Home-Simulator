package domain.house;

import domain.sensors.*;

import java.io.Serializable;
import java.util.List;

public class Room implements Serializable {
    private int id;
    private String roomName;
    private Door door;
    private List<Light> lights;
    private List<Window> windows;
    private AC acUnit;
    private Heater heater;
    private double temperature;

    private Coordinate roomCoordinates;

    public Room(int id, String roomName, Door door, List<Light> lights, List<Window> windows,
                AC acUnit, Heater heater, double temperature, Coordinate roomCoordinates) {
        this.id = id;
        this.roomName = roomName;
        this.door = door;
        this.lights = lights;
        this.windows = windows;
        this.acUnit = acUnit;
        this.heater = heater;
        this.temperature = temperature;
        this.roomCoordinates = roomCoordinates;
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

    public Coordinate getRoomCoordinates() {
        return roomCoordinates;
    }

    public int getX(){
        return roomCoordinates.getX();
    }

    public int getY(){
        return roomCoordinates.getY();
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomName='" + roomName + '\'' +
                ", door=" + door +
                ", lights=" + lights +
                ", windows=" + windows +
                ", acUnit=" + acUnit +
                ", heater=" + heater +
                ", temperature=" + temperature +
                ", roomCoordinates=" + roomCoordinates.getX() + "," +roomCoordinates.getY()+
                '}';
    }
}
