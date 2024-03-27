package domainTest;

import domain.house.House;
import domain.house.Room;
import domain.sensors.Door;
import domain.sensors.Light;
import domain.sensors.Window;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HouseTest {

    private House house;

    @Before
    public void setUp() {
        house = new House();
    }

    @Test
    public void testGetRooms() {
        List<Room> rooms = house.getRooms();
        assertNotNull(rooms);
        assertEquals(9, rooms.size()); // Assuming there are 4 rooms in the default layout
    }

    @Test
    public void testGetRoomNames() {
        List<String> roomNames = house.getRoomNames();
        assertNotNull(roomNames);
        assertEquals(9, roomNames.size()); // Assuming there are 4 rooms in the default layout

    }

    @Test
    public void testGetDoors() {
        List<Door> doors = house.getDoors();
        assertNotNull(doors);

    }

    @Test
    public void testGetWindows() {
        List<Window> windows = house.getWindows();
        assertNotNull(windows);

    }

    @Test
    public void testGetLights() {
        List<Light> lights = house.getLights();
        assertNotNull(lights);

    }



}
