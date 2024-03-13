package domain.sensors;

public abstract class TempControlUnit {
    private int id;
    private boolean isOn = false;

    public TempControlUnit(int id) {
        this.id = id;
    }

    public void turnOn() {
        this.isOn = true;
    }

    public void turnOff() {
        this.isOn = false;
    }

    @Override
    public String toString() {
        return "TempControlUnit{" +
                "id=" + id +
                ", isOn=" + isOn +
                '}';
    }
}
