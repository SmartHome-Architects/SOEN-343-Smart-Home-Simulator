package domainTest;

import domain.smartHomeSimulator.modules.SmartHomeHeating;
import org.junit.Test;

import static org.junit.Assert.*;

public class SmartHomeHeatingTest {

    @Test
    public void testSmartHomeHeatingInitialization() {
        // Create an instance of SmartHomeHeating
        SmartHomeHeating smartHomeHeating = new SmartHomeHeating();

        // Verify that the SmartHomeHeating instance is not null
        assertNotNull(smartHomeHeating);

        // Verify that the isActive flag is initially set to false
        assertFalse(smartHomeHeating.isActive());
    }


}

