package domainTest;
import domain.house.Coordinate;
import domain.sensors.Light;

import domain.house.Coordinate;
import org.junit.Test;
import static org.junit.Assert.*;

public class LightTest {

    @Test
    public void testConstructor() {
        Coordinate coordinates = new Coordinate(1, 2);
        Light light = new Light("Ceiling Light", "Living Room", 1, coordinates);

        assertEquals("Ceiling Light", light.getName());
        assertEquals("Living Room", light.getLocation());
        assertEquals(1, light.getLightID());
        assertFalse(light.isOpen());
        assertEquals(1, light.getX());
        assertEquals(2, light.getY());
    }

    @Test
    public void testSettersAndGetters() {
        Coordinate coordinates = new Coordinate(3, 4);
        Light light = new Light("Ceiling Light", "Living Room", 1, coordinates);

        light.setName("Table Lamp");
        assertEquals("Table Lamp", light.getName());

        light.setLocation("Bedroom");
        assertEquals("Bedroom", light.getLocation());

        light.setLightID(2);
        assertEquals(2, light.getLightID());

        light.setOpen(true);
        assertTrue(light.isOpen());
    }
}
