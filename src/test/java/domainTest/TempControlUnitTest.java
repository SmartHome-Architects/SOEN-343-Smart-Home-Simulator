package domainTest;
import domain.sensors.TempControlUnit;
import domain.house.TempObserver;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TempControlUnitTest {

    private TempControlUnitMock tempControlUnit;

    @Before
    public void setUp() {
        tempControlUnit = new TempControlUnitMock(1); // Creating an instance of TempControlUnit
    }

    @Test
    public void testTurnOn() {
        assertFalse(tempControlUnit.isOn()); // By default, it should be off
        tempControlUnit.turnOn();
        assertTrue(tempControlUnit.isOn()); // After turning on, it should be on
    }

    @Test
    public void testTurnOff() {
        tempControlUnit.turnOn(); // Turn on first
        assertTrue(tempControlUnit.isOn()); // Make sure it's on
        tempControlUnit.turnOff();
        assertFalse(tempControlUnit.isOn()); // After turning off, it should be off
    }



    @Test
    public void testDetach() {
        TempObserverMock observer = new TempObserverMock();
        tempControlUnit.attach(observer);
        tempControlUnit.detach(observer);
        tempControlUnit.turnOn(); // Turning on should not notify the detached observer
        assertFalse(observer.isUpdated()); // Observer should not be updated
    }

    // Mock classes for TempObserver and TempControlUnit for testing
    private class TempObserverMock implements TempObserver {
        private boolean updated = false;

        @Override
        public void update(TempControlUnit unit) {
            updated = true;
        }

        public boolean isUpdated() {
            return updated;
        }
    }

    private class TempControlUnitMock extends TempControlUnit {
        public TempControlUnitMock(int id) {
            super(id);
        }

        // Getter method for testing
        public boolean isOn() {
            return super.isOn;
        }
    }
}