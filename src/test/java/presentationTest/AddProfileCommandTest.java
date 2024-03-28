package presentationTest;
import presentation.Swing.command.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class AddProfileCommandTest {

    @Test
    public void testExecute() {
        // Create an instance of UserAccountManager
        String usersFilePath = "testUsers.txt"; // Provide a test file path if necessary
        UserAccountManager accountManager = new UserAccountManager(usersFilePath);

        // Create an instance of AddProfileCommand with the UserAccountManager instance
        AddProfileCommand command = new AddProfileCommand(accountManager, "testUser", "test@example.com", "password", "access");

        // Call the execute method
        command.execute();
    }
}

