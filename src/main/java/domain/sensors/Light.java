package src.main.java.domain.sensors;

public class Light {
    private String name;
    private String location;
    private int lightID;
    private boolean isOpen; // true for open, false for close

    public Light(String name, String location, int doorID, boolean isOpen) {
        this.name = name;
        this.location = location;
        this.lightID = doorID;
        this.isOpen = isOpen;
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

    @Override
    public String toString() {
        return "Light{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", lightID=" + lightID +
                ", isOpen=" + isOpen +
                '}';
    }
}
