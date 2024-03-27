package domainTest;

import domain.house.Coordinate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoordinateTest {

    @Test
    public void testGetX() {
        Coordinate coordinate = new Coordinate(5, 10);
        assertEquals(5, coordinate.getX());
    }

    @Test
    public void testGetY() {
        Coordinate coordinate = new Coordinate(5, 10);
        assertEquals(10, coordinate.getY());
    }


}
