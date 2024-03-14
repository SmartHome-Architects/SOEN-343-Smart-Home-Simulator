package domain.sensors;

import domain.house.TempObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class TempControlUnit {
    private int id;
    private boolean isOn = false;

    List<TempObserver> observers = new ArrayList<>();

    public TempControlUnit(int id) {
        this.id = id;
    }

    public void turnOn() {
        this.isOn = true;
    }

    public void turnOff() {
        this.isOn = false;
    }

    public void attach(TempObserver observer){
        observers.add(observer);
    }

    public void detach(TempObserver observer){
        observers.remove(observer);
    }

    public void notifyObservers(TempControlUnit unit){
        for (TempObserver observer: observers) {
            observer.update(unit);
        }
    }


    @Override
    public String toString() {
        return "TempControlUnit{" +
                "id=" + id +
                ", isOn=" + isOn +
                '}';
    }
}
