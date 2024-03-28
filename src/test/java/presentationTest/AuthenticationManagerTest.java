package presentationTest;

import presentation.Swing.LoginAndSignUp.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class AuthenticationManagerTest {

    @Test
    public void testAuthenticateAndGetUsername() {
        // Test case with valid email and password
        assertEquals("rana", AuthenticationManager.authenticateAndGetUsername("rana@gmail.com", "rana"));

        // Test case with invalid email
        assertNull(AuthenticationManager.authenticateAndGetUsername("invalid@example.com", "password123"));

        // Test case with invalid password
        assertNull(AuthenticationManager.authenticateAndGetUsername("rana@gmail.com", "invalidpassword"));

        // Test case with null email
        assertNull(AuthenticationManager.authenticateAndGetUsername(null, "password123"));

        // Test case with null password
        assertNull(AuthenticationManager.authenticateAndGetUsername("rana@gmail.com", null));

        // Test case with empty email
        assertNull(AuthenticationManager.authenticateAndGetUsername("", "password123"));

        // Test case with empty password
        assertNull(AuthenticationManager.authenticateAndGetUsername("rana@gmail.com", ""));
    }
}

