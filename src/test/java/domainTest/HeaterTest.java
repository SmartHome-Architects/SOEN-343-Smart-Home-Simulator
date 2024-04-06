package domainTest;
import domain.house.Coordinate;
import domain.sensors.Heater;
import org.junit.Test;
import static org.junit.Assert.*;

public class HeaterTest {


    @Test
    public void testConstructor() {

        int expectedId = 1;


        Heater heater = new Heater(expectedId,new Coordinate(1,2));

        // Assert
        assertNotNull(heater);

    }
}