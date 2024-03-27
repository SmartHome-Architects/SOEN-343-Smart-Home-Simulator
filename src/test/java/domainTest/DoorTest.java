package domainTest;

import domain.sensors.Door; // Import the correct package for Door class
import domain.house.Coordinate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DoorTest {

    private Door door;

    @Before
    public void setUp() {
        Coordinate doorCoordinates = new Coordinate(5, 5); // Sample coordinates
        door = new Door("Front Door", "Living Room", 1, doorCoordinates);
    }

    @Test
    public void testConstructor() {
        assertNotNull(door);
        assertEquals("Front Door", door.getName());
        assertEquals("Living Room", door.getLocation());
        assertEquals(1, door.getDoorID());
        assertFalse(door.isOpen());
        assertEquals(5, door.getX());
        assertEquals(5, door.getY());
    }

    @Test
    public void testSetName() {
        door.setName("Back Door");
        assertEquals("Back Door", door.getName());
    }

    @Test
    public void testSetLocation() {
        door.setLocation("Kitchen");
        assertEquals("Kitchen", door.getLocation());
    }

    @Test
    public void testSetOpen() {
        door.setOpen(true);
        assertTrue(door.isOpen());
    }

    // You can add more test methods to cover other functionalities or edge cases of your Door class.
}
