package presentation.Swing;
import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

        // Write log entry to the file
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

    //Method to log SHH ON/OFF button
    public static void SHHButtonlog(String user, String eventDescription) {
        // File path for the log file
        String logFilePath = "database/LogEntry.txt";

        // Get current timestamp
        String timestamp = getCurrentTimestamp();

        // Construct log entry string
        String logEntryForFile = "Timestamp: " + timestamp + "|" +
                "Device: SHH Module|" +
                "Event Triggered by: " + user + "|" +
                "Event Type: Opening/Closing SHH Module|" +
                "Event Description: " + eventDescription;

        // Write log entry to the log file
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFilePath, true))) {
            writer.println(logEntryForFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String logEntryForConsole = timestamp + "\n" +
                "Device: SHH Module" + "\n" +
                "Event Triggered by: " + user + "\n" +
                "Event Type: Opening/Closing SHH Module"  + "\n" +
                "Event Description: " + eventDescription + "\n";

        // Append log entry to the JTextArea
        textArea.setText(logEntryForConsole + "\n");
    }

    public static void ZoneCreationlog(String user, String zoneName, List<String> selectedRoomNames, String temp, JTextArea textArea1) {
        // File path for the log file
        String logFilePath = "database/LogEntry.txt";

        // Get current timestamp
        String timestamp = getCurrentTimestamp();

        // Construct log entry string
        StringBuilder roomNamesBuilder = new StringBuilder();
        for (String roomName : selectedRoomNames) {
            roomNamesBuilder.append(roomName).append(", ");
        }
        String roomNames = roomNamesBuilder.toString();
        if (roomNames.length() > 0) {
            roomNames = roomNames.substring(0, roomNames.length() - 2); // Remove the last comma and space
        }

        String logEntryForFile = "Timestamp: " + timestamp + "|" +
                "Device: SHH Module|" +
                "Event Triggered by: " + user + "|" +
                "Event Description: Zone Management - Creation of New Zone|" +
                "Zone Name: " + zoneName + "|" +
                "Rooms Added: " + roomNames + "|" +
                "Desired Temperature of Zone: " + temp;

        // Write log entry to the log file
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFilePath, true))) {
            writer.println(logEntryForFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String logEntryForConsole = "Timestamp: " + timestamp + "\n" +
                "Device: SHH Module" + "\n" +
                "Event Triggered by: " + user + "\n" +
                "Event Description: Zone Management - Creation of New Zone" + "\n" +
                "Zone Name: " + zoneName + "\n" +
                "Rooms Added: " + roomNames + "\n" +
                "Desired Temperature of Zone: " + temp + "\n";

        // Append log entry to the JTextArea
        textArea1.setText(logEntryForConsole + "\n");
    }

    public static void Temperaturelog(String user, List<String> modifiedRooms, List<Double> oldTemperatures, List<Double> newTemperatures) {
        // File path for the log file
        String logFilePath = "database/LogEntry.txt";

        // Get current timestamp
        String timestamp = getCurrentTimestamp();

        // Construct log entry string for file
        StringBuilder logEntryForFileBuilder = new StringBuilder();
        logEntryForFileBuilder.append("Timestamp: ").append(timestamp).append("|");
        logEntryForFileBuilder.append("Event Triggered by: ").append(user).append("|");
        logEntryForFileBuilder.append("Event Description: Room Temperature Modification|");

        // Append modified rooms, old and new temperatures to the log entry for file
        for (int i = 0; i < modifiedRooms.size(); i++) {
            String roomName = modifiedRooms.get(i);
            double oldTemperature = oldTemperatures.get(i);
            double newTemperature = newTemperatures.get(i);

            logEntryForFileBuilder.append("Room: ").append(roomName).append(", ");
            logEntryForFileBuilder.append("Old Temperature: ").append(oldTemperature).append(", ");
            logEntryForFileBuilder.append("New Temperature: ").append(newTemperature).append("|");
        }

        String logEntryForFile = logEntryForFileBuilder.toString();

        // Write log entry to the log file
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFilePath, true))) {
            writer.println(logEntryForFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Construct log entry string for console
        StringBuilder logEntryForConsoleBuilder = new StringBuilder();

        // Append modified rooms, old and new temperatures to the log entry for console
        for (int i = 0; i < modifiedRooms.size(); i++) {
            String roomName = modifiedRooms.get(i);
            double oldTemperature = oldTemperatures.get(i);
            double newTemperature = newTemperatures.get(i);

            logEntryForConsoleBuilder.append("Room: ").append(roomName).append(", ");
            logEntryForConsoleBuilder.append("Old Temperature: ").append(oldTemperature).append(", ");
            logEntryForConsoleBuilder.append("New Temperature: ").append(newTemperature).append("\n");
        }

        String logEntryForConsole = logEntryForConsoleBuilder.toString();

        // Append log entry to the JTextArea
        textArea.setText(logEntryForConsole + "\n");
    }





    // Method to get current timestamp
    private static String getCurrentTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

}
