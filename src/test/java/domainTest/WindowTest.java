package domainTest;

import domain.house.Coordinate;
import domain.sensors.Window;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WindowTest {

    @Test
    public void testConstructorAndGetters() {
        Coordinate coordinates = new Coordinate(10, 20);
        Window window = new Window("Living Room Window", "Living Room", 1, coordinates);

        assertEquals("Living Room Window", window.getName());
        assertEquals("Living Room", window.getLocation());
        assertEquals(1, window.getWindowID());
        assertEquals(10, window.getX());
        assertEquals(20, window.getY());
        assertEquals(false, window.isOpen()); // Initially should be closed
        assertEquals(false, window.isBlocked()); // Initially should not be blocked
    }

    @Test
    public void testSetters() {
        Coordinate coordinates = new Coordinate(15, 25);
        Window window = new Window("Bedroom Window", "Bedroom", 2, coordinates);

        window.setName("Kitchen Window");
        window.setLocation("Kitchen");
        window.setWindowID(3);
        window.setOpen(true);
        window.setBlocked(true);

        assertEquals("Kitchen Window", window.getName());
        assertEquals("Kitchen", window.getLocation());
        assertEquals(3, window.getWindowID());
        assertEquals(true, window.isOpen());
        assertEquals(true, window.isBlocked());
    }
}
