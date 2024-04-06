

package domainTest;

import static org.junit.Assert.*;

import domain.house.Coordinate;
import org.junit.Test;
import domain.sensors.AC;

public class ACTest {

    @Test
    public void testConstructor() {
        AC ac = new AC(1, new Coordinate(1,1));
        assertNotNull(ac);
    }
//test passed

}
