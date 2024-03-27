package domainTest;

import domain.house.Observer;
import org.junit.Test;
import static org.junit.Assert.*;

public class ObserverTest {

    @Test
    public void testObserverImplementation() {
        // Create a mock implementation of the Observer interface
        Observer mockObserver = new Observer() {
            @Override
            public void update(double tempRate, boolean isActive, double outsideTemp) {
                // Implement update method behavior for testing
                // Here you can add assertions to verify the behavior
                // For example:
                assertTrue(isActive);
                assertEquals(25.0, outsideTemp, 0.01); // Assuming outside temp is 25.0
            }
        };

        // Call the update method on the mockObserver
        mockObserver.update(0.5, true, 25.0); // Assuming some values for testing
    }
}
