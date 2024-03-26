package presentation.Swing.command;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap;
import java.util.Map;

public class UserAccountManager {
    private final File loggedInUserFile;
    private final File usersFile;
    private final String databaseDirectory;

    public UserAccountManager(String usersFilePath) {
        this.loggedInUserFile = new File("database/userNameLoggedIn.txt");
        this.usersFile = new File(usersFilePath);
        this.databaseDirectory = "database/";
    }

    public void addUser(String username, String email, String password, String accessibility) {
        String loggedInUsername = getLoggedInUsername();
        if (loggedInUsername != null) {
            // Append user to users.txt
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(databaseDirectory + "Users.txt", true))) {
                writer.write(username + "|" + email + "|" + password + "|" + accessibility);
                writer.newLine();
            } catch (IOException e) {
                handleFileError("Error adding user", e);
            }

            // Append user to the file named after the logged-in user
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(databaseDirectory + loggedInUsername + ".txt", true))) {
                writer.write(username + "|" + email + "|" + password + "|" + accessibility);
                writer.newLine();
            } catch (IOException e) {
                handleFileError("Error adding user", e);
            }
        } else {
            System.err.println("No user is logged in. Cannot add user.");
        }
    }

    public void deleteUser(String username) {
        String loggedInUsername = getLoggedInUsername();
        if (loggedInUsername != null) {
            File userFile = new File(databaseDirectory + loggedInUsername + ".txt");
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.startsWith(username + "|")) {
                        lines.add(line);
                    }
                }
            } catch (IOException e) {
                handleFileError("Error deleting user", e);
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                handleFileError("Error deleting user", e);
            }
        } else {
            System.err.println("No user is logged in.");
        }
    }

    public void editUser(String oldUsername, String username, String email, String password, String accessibility) {
        String loggedInUsername = getLoggedInUsername();
        if (loggedInUsername != null) {
            File userFile = new File(databaseDirectory + loggedInUsername + ".txt");
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith(oldUsername + "|")) {
                        line = username + "|" + email + "|" + password + "|" + accessibility;
                    }
                    lines.add(line);
                }
            } catch (IOException e) {
                handleFileError("Error editing user", e);
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                handleFileError("Error editing user", e);
            }
        } else {
            System.err.println("No user is logged in.");
        }
    }

    public String getLoggedInUsername() {
        try (BufferedReader reader = new BufferedReader(new FileReader(loggedInUserFile))) {
            return reader.readLine(); // Assuming only one username is stored per line
        } catch (IOException e) {
            handleFileError("Error getting logged-in user", e);
        }
        return null; // Return null if unable to read the file
    }


    // Method to get the text file associated with the logged-in user's username
    public File getLoggedInUserTextFile() {
        String loggedInUsername = getLoggedInUsername();
        if (loggedInUsername != null) {
            return new File(databaseDirectory + loggedInUsername + ".txt");
        } else {
            System.err.println("No user is logged in.");
            return null;
        }
    }


    public List<String> getFirstColumnContent(String username) {
        List<String> firstColumnContent = new ArrayList<>();
        File userFile = new File(databaseDirectory + username + ".txt");
        if (userFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Split the line by "|" and add only the first column to the list
                    String[] columns = line.split("\\|");
                    if (columns.length > 0) {
                        firstColumnContent.add(columns[0]); // Adding the first column
                    }
                }
            } catch (IOException e) {
                handleFileError("Error reading user content", e);
            }
        }
        return firstColumnContent;
    }



    private void handleFileError(String message, IOException e) {
        System.err.println(message);
        e.printStackTrace();
    }

    public String getUserLocation(String username) {
        String location = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(usersFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 5 && parts[0].equals(username)) {
                    location = parts[4]; // Assuming location is stored in the fifth column
                    break;
                }
            }
        } catch (IOException e) {
            handleFileError("Error reading Users.txt", e);
        }
        return location;
    }

    public void updateUserLocation(String username, String newLocation) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(usersFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 5 && parts[0].equals(username)) {
                    parts[4] = newLocation; // Update the location
                }
                lines.add(String.join("|", parts)); // Reconstruct the line
            }
        } catch (IOException e) {
            handleFileError("Error updating user location", e);
            return;
        }
        // Write the updated content back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(usersFile))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            handleFileError("Error updating user location", e);
        }
    }

}
