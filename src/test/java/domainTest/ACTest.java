

package domainTest;

import static org.junit.Assert.*;
import org.junit.Test;
import domain.sensors.AC;

public class ACTest {

    @Test
    public void testConstructor() {
        AC ac = new AC(1);
        assertNotNull(ac);
    }

    // You can add more test methods to cover other functionalities or edge cases of your AC class.
}
