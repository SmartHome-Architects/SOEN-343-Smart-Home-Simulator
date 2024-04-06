package domain.sensors;

import domain.house.Coordinate;
import domain.house.TempObserver;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class TempControlUnit {
    private int id;
    private boolean isOn = false;

    List<TempObserver> observers = new ArrayList<>();

    private Coordinate unitCoordinates;

    private JLabel tempUnitLabel;

    public TempControlUnit(int id, Coordinate coordinates) {
        this.id = id;
        this.unitCoordinates = coordinates;
    }

    public void setIsOn(boolean status) {
        isOn = status;
    }

    public JLabel getTempUnitLabel() {
        return tempUnitLabel;
    }

    public void setTempUnitLabel(JLabel tempUnitLabel) {
        this.tempUnitLabel = tempUnitLabel;
    }

    public boolean isOn() {
        return isOn;
    }

    public int getX(){
        return unitCoordinates.getX();
    }

    public int getY(){
        return unitCoordinates.getY();
    }

    public abstract void turnOn();

    public abstract void turnOff();

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
