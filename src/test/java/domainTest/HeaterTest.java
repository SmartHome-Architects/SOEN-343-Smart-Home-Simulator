package domainTest;
import domain.sensors.Heater;
import org.junit.Test;
import static org.junit.Assert.*;

public class HeaterTest {


    @Test
    public void testConstructor() {

        int expectedId = 1;


        Heater heater = new Heater(expectedId);

        // Assert
        assertNotNull(heater);

    }
}