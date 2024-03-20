package presentation.Swing;
import javax.swing.JTextArea;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogEntry {

        private static JTextArea textArea; // Reference to the JTextArea

        // Method to set the JTextArea reference
        public static void setTextArea(JTextArea area) {
            textArea = area;
        }

    public static void logEntry(String deviceID, String eventType, String eventDescription) {
        Profilelog(deviceID, eventType, eventDescription);
    }

        // Method to Log User Profiles (Add, Delete, Edit)
        public static void Profilelog(String deviceID, String eventType, String eventDescription) {
            // File path for the log file
            String logFilePath = "database/LogEntries.txt";

            // Get current timestamp
            String timestamp = getCurrentTimestamp();

            // Construct log entry string
            String logEntryForFile = "Timestamp: " + timestamp + "|" +
                    "Device: " + deviceID + "|" +
                    "Event Type: " + eventType + "|" +
                    "Event Description: " + eventDescription;

            // Write log entry to the log file
            try (PrintWriter writer = new PrintWriter(new FileWriter(logFilePath, true))) {
                writer.println(logEntryForFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String logEntryForConsole = timestamp + "\n" +
                    "Device: " + deviceID + "\n" +
                    "Event Type: " + eventType + "\n" +
                    "Event Description: " + eventDescription + "\n";

            // Append log entry to the JTextArea
            textArea.setText(logEntryForConsole + "\n");
        }

        // Method to get current timestamp
        private static String getCurrentTimestamp() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.format(new Date());
        }
    }

