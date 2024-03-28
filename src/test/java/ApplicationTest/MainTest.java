package ApplicationTest;

import Application.Main;
import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testMainMethod() {
        // Execute the main method of the Main class
        // Ensure that it does not throw any exceptions
        try {
            Main.main(new String[]{});
            // If the main method is executed without exceptions,
            // we assume that the GUI is launched successfully
            assertTrue(true);
        } catch (Exception e) {
            // If an exception is caught, fail the test
            fail("Exception occurred: " + e.getMessage());
        }
    }
}
