package domainTest;

import domain.smartHomeSimulator.SmartHomeSimulator;
import org.junit.Test;

import static org.junit.Assert.*;

public class SmartHomeSimulatorTest {

    @Test
    public void testSmartHomeSimulatorConstructor() {
        SmartHomeSimulator smartHomeSimulator = new SmartHomeSimulator();
        assertNotNull(smartHomeSimulator);
    }
}
