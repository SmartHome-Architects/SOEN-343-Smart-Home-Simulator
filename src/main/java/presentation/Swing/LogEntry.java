package presentation.Swing;
import javax.swing.JTextArea;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogEntry {

    private static JTextArea textArea; // Reference to the JTextArea

    // Method to set the JTextArea reference
    public static void setTextArea(JTextArea area) {
        textArea = area;
    }

    public static void logEntry(String user, String deviceID, String eventType, String eventDescription) {
        Profilelog(user,deviceID, eventType, eventDescription);
    }

    // Method to Log User Profiles (Add, Delete, Edit)
    public static void Profilelog(String user, String deviceID, String eventType, String eventDescription) {
        // File path for the log file
        String logFilePath = "database/LogEntry.txt";

        // Get current timestamp
        String timestamp = getCurrentTimestamp();

        // Construct log entry string
        String logEntryForFile = "Timestamp: " + timestamp + "|" +
                "Device: " + deviceID + "|" +
                "Event Triggered by: " + user + "|" +
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
                "Event Triggered by: " + user + "\n" +
                "Event Type: " + eventType + "\n" +
                "Event Description: " + eventDescription + "\n";

        // Append log entry to the JTextArea
        textArea.setText(logEntryForConsole + "\n");
    }


    //Method to log Date and Time
    public static void DateTimelog(String user, String deviceID, String eventType, String eventDescription,
                                  String oldDate, String newDate) {
        // File path for the log file
        String logFilePath = "database/LogEntry.txt";

        // Get current timestamp
        String timestamp = getCurrentTimestamp();

        // Construct log entry string
        String logEntryForFile = "Timestamp: " + timestamp + "|" +
                "Device: " + deviceID + "|" +
                "Event Triggered by: " + user + "|" +
                "Event Type: " + eventType + "|" +
                "Event Description: " + eventDescription + "|" +
                "Old Date/Time: " + oldDate + "|" +
                "New Date/Time: " + newDate;

        // Write log entry to the log file
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFilePath, true))) {
            writer.println(logEntryForFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Log the event with old and new dates
        String logEntryForConsole = timestamp + "\n" +
                "Device: " + deviceID + "\n" +
                "Event Triggered by: " + user + "\n" +
                "Event Type: " + eventType + "\n" +
                "Event Description: " + eventDescription + "\n" +
                "Old Date/Time: " + oldDate + "\n" +
                "New Date/Time: " + newDate + "\n";

        // Append log entry to the JTextArea
        textArea.setText(logEntryForConsole + "\n");
    }

    // Method to Log Location Changes
    public static void LocationLog(String user, String inhabitant, String oldLocation, String newLocation) {
        // File path for the log file
        String logFilePath = "database/LogEntry.txt";

        // Get current timestamp
        String timestamp = getCurrentTimestamp();

        // Construct log entry string
        String logEntryString = "Timestamp: " + timestamp + "|" +
                "Event Triggered by: " + user + "|" +
                "Event Type: Location Change of Inhabitant: " + inhabitant + "|" +
                "Old Location: " + oldLocation + "|"+
                "New Location: " + (newLocation != null ? newLocation : "Unknown");

        // Write log entry to the log file
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFilePath, true))) {
            writer.println(logEntryString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Log the event with old and new locations
        String logEntryForConsole = timestamp + "\n" +
                "Event Triggered by: " + user + "\n" +
                "Event Type: Location Change" + "\n" +
                "Event Description: Moving Inhabitant " + inhabitant + " to a new location" + "\n" +
                "Old Location: " + oldLocation + "\n" +
                "New Location: " + newLocation + "\n";

        textArea.setText(logEntryForConsole + "\n");
    }

    public static void Permissionlog(String user, String deviceID, String eventType, String eventDescription) {
        // File path for the log file
        String logFilePath = "database/LogEntry.txt";

        // Get current timestamp
        String timestamp = getCurrentTimestamp();

        // Construct log entry string
        String logEntryForFile = "Timestamp: " + timestamp + "|" +
                "Device: " + deviceID + "|" +
                "Event Triggered by: " + user + "|" +
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
                "Event Triggered by: " + user + "\n" +
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
