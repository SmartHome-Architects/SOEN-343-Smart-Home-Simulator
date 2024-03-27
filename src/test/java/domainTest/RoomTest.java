package domainTest;

import domain.house.Coordinate;
import domain.house.Room;
import domain.sensors.AC;
import domain.sensors.Door;
import domain.sensors.Heater;
import domain.sensors.Light;
import domain.sensors.Window;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RoomTest {

    @Test
    public void testRoomInitialization() {
        // Create sample objects for testing
        Door door = new Door("Main Door", "Living Room", 1, new Coordinate(1, 1));
        List<Light> lights = new ArrayList<>();
        List<Window> windows = new ArrayList<>();
        AC acUnit = new AC(1);
        Heater heater = new Heater(1);
        double temperature = 20.0;
        Coordinate roomCoordinates = new Coordinate(10, 10);

        // Create a Room object
        Room room = new Room(1, "Living Room", door, lights, windows, acUnit, heater, temperature, roomCoordinates);

        // Assert that the Room object is not null
        assertNotNull(room);

        // Test getter methods
        assertEquals(1, room.getId());
        assertEquals("Living Room", room.getRoomName());
        assertEquals(door, room.getDoor());
        assertEquals(lights, room.getLights());
        assertEquals(windows, room.getWindows());
        assertEquals(acUnit, room.getAcUnit());
        assertEquals(heater, room.getHeater());
        assertEquals(20.0, room.getTemperature(), 0.01);
        assertEquals(roomCoordinates, room.getRoomCoordinates());
        assertEquals(10, room.getX());
        assertEquals(10, room.getY());
    }

    // Add more test cases as needed

}
