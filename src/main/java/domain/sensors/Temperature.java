package src.main.java.domain.sensors;

public class Temperature {
    private String name;
    private String location;
    private int thermostatID;
    private double thermostatReading;

    public Temperature(String name, String location, int thermostatID, double thermostatReading) {
        this.name = name;
        this.location = location;
        this.thermostatID = thermostatID;
        this.thermostatReading = thermostatReading;
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

    public int getThermostatID() {
        return thermostatID;
    }

    public void setThermostatID(int thermostatID) {
        this.thermostatID = thermostatID;
    }

    public double getThermostatReading() {
        return thermostatReading;
    }

    public void setThermostatReading(double thermostatReading) {
        this.thermostatReading = thermostatReading;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", thermostatID=" + thermostatID +
                ", thermostatReading=" + thermostatReading +
                '}';
    }
}
