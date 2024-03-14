import org.junit.Test;
import presentation.Swing.LoginAndSignUp.LogIn;

import static org.junit.Assert.assertEquals;

public class LogInTest {

    @Test
    public void testLoginWithEmailAndPassword() {
        LogIn logIn = new LogIn();

        // Set email and password
        logIn.jTextField1.setText("test@example.com");

        // Assert that the email and password are correctly set
        assertEquals("test@example.com", logIn.getEmailText());
    }
}
