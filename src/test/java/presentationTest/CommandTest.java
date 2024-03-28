package presentationTest;

import presentation.Swing.command.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class CommandTest {

    @Test
    public void testExecute() {
        // Create a mock command implementation for testing
        MockCommand mockCommand = new MockCommand();

        // Execute the command
        mockCommand.execute();

        // Check if the command was executed
        assertTrue(mockCommand.isExecuted());
    }

    // Mock implementation of the Command interface
    private static class MockCommand implements Command {
        private boolean executed = false;

        @Override
        public void execute() {
            executed = true;
        }

        // Getter method to check if the command was executed
        public boolean isExecuted() {
            return executed;
        }
    }
}
