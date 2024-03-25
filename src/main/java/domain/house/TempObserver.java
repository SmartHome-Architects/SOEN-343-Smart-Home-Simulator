package domain.house;

import domain.sensors.TempControlUnit;

public interface TempObserver {
    void update(TempControlUnit unit);
}
