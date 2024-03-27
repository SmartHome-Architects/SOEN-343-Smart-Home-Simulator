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
        // Add more assertions as needed based on the specific layout
    }

    @Test
    public void testGetDoors() {
        List<Door> doors = house.getDoors();
        assertNotNull(doors);
        // Add assertions based on the expected number of doors in the layout
    }

    @Test
    public void testGetWindows() {
        List<Window> windows = house.getWindows();
        assertNotNull(windows);
        // Add assertions based on the expected number of windows in the layout
    }

    @Test
    public void testGetLights() {
        List<Light> lights = house.getLights();
        assertNotNull(lights);
        // Add assertions based on the expected number of lights in the layout
    }

    // Add more test cases as needed

}
