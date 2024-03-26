package domain.sensors;

import domain.house.Coordinate;

public class Light {
    private String name;
    private String location;
    private int lightID;
    private boolean isOpen; // true for open, false for close

    private Coordinate lightCoordinates;

    public Light(String name, String location, int doorID, Coordinate lightCoordinates) {
        this.name = name;
        this.location = location;
        this.lightID = doorID;
        this.isOpen = false;
        this.lightCoordinates = lightCoordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLightID() {
        return lightID;
    }

    public void setLightID(int doorID) {
        this.lightID = doorID;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public int getX(){
        return lightCoordinates.getX();
    }

    public int getY(){
        return lightCoordinates.getY();
    }

    @Override
    public String toString() {
        return "Light{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", lightID=" + lightID +
                ", isOpen=" + isOpen +
                ", lightCoordinates=" + lightCoordinates.getX() + "," + lightCoordinates.getY()+
                '}';
    }
}
