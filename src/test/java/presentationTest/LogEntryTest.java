package presentationTest;

import org.junit.jupiter.api.Test;
import presentation.Swing.LogEntry;

import javax.swing.JTextArea;
import static org.junit.jupiter.api.Assertions.*;

public class LogEntryTest {

    @Test
    void Profilelog_LogsToConsoleAndFile() {
        // Arrange
        JTextArea textArea = new JTextArea();
        LogEntry.setTextArea(textArea);
        String user = "hello";
        String deviceID = "Device123";
        String eventType = "TestEvent";
        String eventDescription = "This is a test event.";

        // Act
        LogEntry.Profilelog(user,deviceID, eventType, eventDescription);

        // Assert
        String consoleLog = textArea.getText();
        assertTrue(consoleLog.contains(eventType), "Console log should contain event type");
        assertTrue(consoleLog.contains(eventDescription), "Console log should contain event description");

    }
}
