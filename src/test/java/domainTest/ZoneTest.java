package domainTest;

import domain.house.Zone;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ZoneTest {

    @Test
    public void testZoneInitialization() {
        String zoneName = "Living Room";
        double desiredZoneTemperature = 22.0;

        // Create a Zone object
        Zone zone = new Zone(zoneName, desiredZoneTemperature);

        // Check if the zone object is not null
        assertNotNull(zone);

        // Check if the zone name is set correctly
        assertEquals(zoneName, zone.getZoneName());

        // Check if the desired zone temperature is set correctly
        assertEquals(desiredZoneTemperature, zone.getDesiredZoneTemperature(), 0.01);
    }

    // Add more test cases as needed

}
