package domain.smartHomeSimulator.modules;

import domain.house.Zone;

public interface Observable {
    public void attach(Zone zone);
    public void detach(Zone zone);
    public void notifyObservers();
}
