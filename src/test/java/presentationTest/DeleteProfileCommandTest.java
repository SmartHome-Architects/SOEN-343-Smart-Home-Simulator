package presentationTest;



import org.junit.Test;
import presentation.Swing.command.*;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DeleteProfileCommandTest {

    @Test
    public void testExecute() throws IOException {
        // Create an instance of UserAccountManager with the same test file path used in AddProfileCommandTest
        String usersFilePath = "testUsers.txt";
        UserAccountManager accountManager = new UserAccountManager(usersFilePath);

        // Add a test user using AddProfileCommand
        AddProfileCommand addProfileCommand = new AddProfileCommand(accountManager, "testUser", "test@example.com", "password", "access");
        addProfileCommand.execute();

        // Create an instance of DeleteProfileCommand with the same UserAccountManager instance
        DeleteProfileCommand deleteProfileCommand = new DeleteProfileCommand(accountManager, "testUser");

        // Call the execute method
        deleteProfileCommand.execute();

        // Verify that the user profile entry was deleted
        File userFile = accountManager.getLoggedInUserTextFile();
        assertTrue(userFile.exists()); // Ensure the user file exists
        assertFalse(accountManager.getFirstColumnContent("testUser").contains("testUser")); // Ensure the user profile entry is deleted
    }
}

