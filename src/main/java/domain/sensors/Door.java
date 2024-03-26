package domain.sensors;

import domain.house.Coordinate;

public class Door {
    private String name;
    private String location;
    private int doorID;
    private boolean isOpen; // true for open, false for close

    private Coordinate doorCoordinates;

    public Door(String name, String location, int doorID,Coordinate doorCoordinates) {
        this.name = name;
        this.location = location;
        this.doorID = doorID;
        this.isOpen = false;
        this.doorCoordinates = doorCoordinates;
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

    public int getDoorID() {
        return doorID;
    }

    public void setDoorID(int doorID) {
        this.doorID = doorID;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public int getX(){
        return doorCoordinates.getX();
    }

    public int getY(){
        return doorCoordinates.getY();
    }

    @Override
    public String toString() {
        return "Door{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", doorID=" + doorID +
                ", isOpen=" + isOpen +
                ", doorCoordinates=" + doorCoordinates.getX() + "," +doorCoordinates.getY()+
                '}';
    }
}
