package presentationTest;

import org.junit.Test;
import presentation.Swing.LoginAndSignUp.LoginAndSignUp;

import static org.junit.Assert.*;

public class LoginAndSignUpTest {

    @Test
    public void testMainMethod() {
        // Test that the main method does not throw any exceptions
        try {
            LoginAndSignUp.main(new String[0]);
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}
