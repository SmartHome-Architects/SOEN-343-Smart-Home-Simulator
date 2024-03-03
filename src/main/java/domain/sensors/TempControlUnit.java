package src.main.java.domain.sensors;

public abstract class TempControlUnit {
    private int id;
    private boolean isOn = false;
    private double temperature;

    public TempControlUnit(int id,double temperature){
        this.id = id;
        this.temperature = temperature;
    }

    public void turnOn(){
        this.isOn = true;
    }

    public void turnOff(){
        this.isOn = false;
    }

    public void setTemperature(double newTemperature){
        this.temperature = newTemperature;
    }

    public double getTemperature(){
        return temperature;
    }
}
