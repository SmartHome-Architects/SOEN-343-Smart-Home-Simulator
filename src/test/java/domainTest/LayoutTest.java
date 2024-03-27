package domainTest;

import domain.house.Layout;
import domain.house.Room;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LayoutTest {

    @Test
    public void testLoadLayout() {
        List<Room> rooms = Layout.loadLayout();

        assertNotNull(rooms);
        assertEquals(9, rooms.size());

    }
}