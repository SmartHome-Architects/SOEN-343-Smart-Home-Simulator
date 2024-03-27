package domainTest;

import domain.smartHomeSimulator.modules.SmartHomeSecurity;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SmartHomeSecurityTest {

    @Test
    public void testSmartHomeSecurityInitialization() {
        // Create an instance of SmartHomeSecurity
        SmartHomeSecurity smartHomeSecurity = new SmartHomeSecurity();

        // Verify that the SmartHomeSecurity instance is not null
        assertNotNull(smartHomeSecurity);
    }

    // You can add more test methods to test other functionalities of SmartHomeSecurity class
}
