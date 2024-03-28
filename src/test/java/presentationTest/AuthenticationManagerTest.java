package presentationTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import presentation.Swing.LoginAndSignUp.AuthenticationManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.*;

public class AuthenticationManagerTest {

    private static final String TEST_FILE_PATH = "testUsers.txt";

    @Before
    public void setUp() {
        // Create a test file with user credentials
        try (PrintWriter writer = new PrintWriter(TEST_FILE_PATH)) {
            writer.println("TestUser1|test1@example.com|hashedPassword1");
            writer.println("TestUser2|test2@example.com|hashedPassword2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        // Delete the test file
        File file = new File(TEST_FILE_PATH);
        file.delete();
    }

    @Test
    public void testAuthenticateUser_ValidCredentials() {
        assertTrue(AuthenticationManager.authenticateUser("test1@example.com", "password123"));
    }

    // Other test methods
}
