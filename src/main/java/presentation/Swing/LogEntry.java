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

    public static void logEntry(String deviceID, String eventType, String eventDescription) {
        Profilelog(deviceID, eventType, eventDescription);
    }

    // Method to Log User Profiles (Add, Delete, Edit)
    public static void Profilelog(String deviceID, String eventType, String eventDescription) {
        // File path for the log file
        String logFilePath = "database/LogEntry.txt";

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


    //
    public static void DateTimelog(String deviceID, String eventType, String eventDescription,
                                  String oldDate, String newDate) {
        // File path for the log file
        String logFilePath = "database/LogEntry.txt";

        // Get current timestamp
        String timestamp = getCurrentTimestamp();

        // Construct log entry string
        String logEntryForFile = "Timestamp: " + timestamp + "|" +
                "Device: " + deviceID + "|" +
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
        String logEntryForConsole = timestamp + "" +
                "Device: " + deviceID + "\n" +
                "Event Type: " + eventType + "\n" +
                "Event Description: " + eventDescription + "\n" +
                "Old Date/Time: " + oldDate + "\n" +
                "New Date/Time: " + newDate + "\n";

        // Append log entry to the JTextArea
        textArea.setText(logEntryForConsole + "\n");
    }
    // Method to Log Location Changes
    public static void LocationLog(String deviceID, String oldLocation, String newLocation) {
        // File path for the log file
        String logFilePath = "database/LogEntry.txt";

        // Get current timestamp
        String timestamp = getCurrentTimestamp();

        // Construct log entry string
        String logEntryString = timestamp + "|" +
                "Device: " + deviceID + "|" +
                "Event Type: Location Change|" +
                "Old Location: " + oldLocation + "|"+
                "New Location: " + (newLocation != null ? newLocation : "Unknown") + "|";

        // Read existing log entries
        StringBuilder logContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logContent.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Append new log entry to the log content
        logContent.append(logEntryString).append("\n");

        // Write log content back to the log file
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFilePath))) {
            writer.println(logContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set log entry to JTextArea
        textArea.setText(logContent.toString());
    }



    // Method to get current timestamp
    private static String getCurrentTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

}
