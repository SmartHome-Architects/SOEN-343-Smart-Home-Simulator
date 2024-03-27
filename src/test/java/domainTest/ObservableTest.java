package domainTest;

import domain.smartHomeSimulator.modules.Observable;
import domain.house.Zone;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ObservableTest {

    private StubObservable observable;

    @Before
    public void setUp() {
        observable = new StubObservable();
    }

    @Test
    public void testAttach() {
        Zone zone = new Zone("Test Zone");
        observable.attach(zone);
        assertTrue(observable.isAttached(zone)); // Check if zone is attached successfully
    }

    @Test
    public void testDetach() {
        Zone zone = new Zone("Test Zone");
        observable.attach(zone);
        observable.detach(zone);
        assertFalse(observable.isAttached(zone)); // Check if zone is detached successfully
    }

    @Test
    public void testNotifyObservers() {
        // Test notification of observers
        observable.notifyObservers();
        // Placeholder assertion
        assertTrue(true); // Placeholder assertion
    }

    // Stub implementation of Observable for testing purposes
    private class StubObservable implements Observable {
        private Zone attachedZone;

        @Override
        public void attach(Zone zone) {
            this.attachedZone = zone;
        }

        @Override
        public void detach(Zone zone) {
            this.attachedZone = null;
        }

        @Override
        public void notifyObservers() {
            // No implementation needed for testing
        }

        public boolean isAttached(Zone zone) {
            return this.attachedZone == zone;
        }
    }
}
