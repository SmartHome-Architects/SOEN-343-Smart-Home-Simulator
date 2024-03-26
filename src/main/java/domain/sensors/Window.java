package domain.sensors;

import domain.house.Coordinate;

public class Window {
    private String name;
    private String location;
    private int windowID;
    private boolean isOpen; // true for open, false for close
    private boolean isBlocked; //true for blocked, false for not blocked

    private Coordinate windowCoordinates;

    public Window(String name, String location, int windowID,Coordinate windowCoordinates ) {
        this.name = name;
        this.location = location;
        this.windowID = windowID;
        this.isOpen = isOpen;
        this.isBlocked = false;
        this.windowCoordinates = windowCoordinates;
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

    public int getWindowID() {
        return windowID;
    }

    public void setWindowID(int windowID) {
        this.windowID = windowID;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public int getX(){
        return windowCoordinates.getX();
    }

    public int getY(){
        return windowCoordinates.getY();
    }

    @Override
    public String toString() {
        return "Window{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", windowID=" + windowID +
                ", isOpen=" + isOpen +
                ", isBlocked=" + isBlocked +
                ", windowCoordinates=" + windowCoordinates.getX() + "," + windowCoordinates.getY()+
                '}';
    }
}
