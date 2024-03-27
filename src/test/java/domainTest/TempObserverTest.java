package domainTest;

import domain.house.TempObserver;
import domain.sensors.TempControlUnit;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TempObserverTest {

    @Test
    public void testTempObserver() {
        // Create a mock implementation of TempObserver (not needed for an interface)
        TempObserver tempObserver = new TempObserver() {
            @Override
            public void update(TempControlUnit unit) {
                // Implementation is not needed for this test
            }
        };

        // Assert that the TempObserver object is not null
        assertNotNull(tempObserver);
    }

    // Add more test cases as needed

}
