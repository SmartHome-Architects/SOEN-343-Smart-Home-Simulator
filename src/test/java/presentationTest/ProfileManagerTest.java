package presentationTest;

import presentation.Swing.command.Command;
import presentation.Swing.command.ProfileManager;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProfileManagerTest {

    private class TestCommand implements Command {
        private boolean executed = false;

        @Override
        public void execute() {
            executed = true;
        }

        public boolean isExecuted() {
            return executed;
        }
    }

    @Test
    public void testAddProfile() {
        TestCommand addProfileCommand = new TestCommand();
        TestCommand deleteProfileCommand = new TestCommand();
        TestCommand editProfileCommand = new TestCommand();

        ProfileManager profileManager = new ProfileManager(addProfileCommand, deleteProfileCommand, editProfileCommand);

        profileManager.addProfile();

        assertTrue(addProfileCommand.isExecuted());
        assertFalse(deleteProfileCommand.isExecuted());
        assertFalse(editProfileCommand.isExecuted());
    }

    @Test
    public void testDeleteProfile() {
        TestCommand addProfileCommand = new TestCommand();
        TestCommand deleteProfileCommand = new TestCommand();
        TestCommand editProfileCommand = new TestCommand();

        ProfileManager profileManager = new ProfileManager(addProfileCommand, deleteProfileCommand, editProfileCommand);

        profileManager.deleteProfile();

        assertFalse(addProfileCommand.isExecuted());
        assertTrue(deleteProfileCommand.isExecuted());
        assertFalse(editProfileCommand.isExecuted());
    }

    @Test
    public void testEditProfile() {
        TestCommand addProfileCommand = new TestCommand();
        TestCommand deleteProfileCommand = new TestCommand();
        TestCommand editProfileCommand = new TestCommand();

        ProfileManager profileManager = new ProfileManager(addProfileCommand, deleteProfileCommand, editProfileCommand);

        profileManager.editProfile();

        assertFalse(addProfileCommand.isExecuted());
        assertFalse(deleteProfileCommand.isExecuted());
        assertTrue(editProfileCommand.isExecuted());
    }
}

