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
        // Check if the email is in the correct format


        if (!isValidEmail(email)) {
            System.err.println("Error: Invalid email format. Please provide a valid email address.");
            return;
        }

        // Check if the username already exists
        if (isUsernameTaken(username)) {
            System.err.println("Error: Username already exists. Please choose a different username.");
            return;
        }

        String loggedInUsername = getLoggedInUsername();
        if (loggedInUsername != null) {

            String defaultLocation = "Entrance"; // Set default location to "Entrance"

            // Append user to users.txt
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(databaseDirectory + "Users.txt", true))) {
                writer.write(username + "|" + email + "|" + password + "|" + accessibility + "|" + defaultLocation);
                writer.newLine();
            } catch (IOException e) {
                handleFileError("Error adding user", e);
            }

            // Append user to the file named after the logged-in user
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(databaseDirectory + loggedInUsername + ".txt", true))) {
                writer.write(username + "|" + email + "|" + password + "|" + accessibility + "|" + defaultLocation);
                writer.newLine();
            } catch (IOException e) {
                handleFileError("Error adding user", e);
            }
        } else {
            System.err.println("No user is logged in. Cannot add user.");
        }
    }

    // Helper method to validate email format
    public boolean isValidEmail(String email) {
        // You can implement your own email validation logic here
        // This is a simple example, you might want to use a regular expression for more comprehensive validation
        return email != null && email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }
    // Helper method to check if the username is already taken
    public boolean isUsernameTaken(String username) {
        // Implement logic to check if the username already exists in the system
        // You might need to read existing user data from a file or a database to perform this check
        // For demonstration purposes, let's assume a list of existing usernames
        List<String> existingUsernames = getAllUsernames(); // Implement this method to get existing usernames
        return existingUsernames.contains(username);
    }


    private List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(usersFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length > 0) {
                    usernames.add(parts[0]); // Adding the username
                }
            }
        } catch (IOException e) {
            handleFileError("Error reading user data", e);
        }
        return usernames;
    }



    public void deleteUser(String username) {
        String loggedInUsername = getLoggedInUsername();
        if (loggedInUsername != null) {
            File userFile = new File(databaseDirectory + loggedInUsername + ".txt");
            List<String> lines = new ArrayList<>();
            boolean removeFromLoggedInUser = false; // Flag to determine if user needs to be removed from loggedInUsername.txt
            try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.startsWith(username + "|")) {
                        lines.add(line);
                    } else {
                        removeFromLoggedInUser = true; // User found in loggedInUsername.txt, flag for removal
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

            // If the user was found in loggedInUsername.txt, remove them from users.txt as well
            if (removeFromLoggedInUser) {
                removeUserFromUsersFile(username);
            }
        } else {
            System.err.println("No user is logged in.");
        }
    }



    private void removeUserFromUsersFile(String username) {
        List<String> updatedLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(usersFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(username + "|")) {
                    updatedLines.add(line);
                }
            }
        } catch (IOException e) {
            handleFileError("Error removing user from users file", e);
            return;
        }

        // Write the updated content back to the original file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(usersFile))) {
            for (String updatedLine : updatedLines) {
                writer.write(updatedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            handleFileError("Error updating users file", e);
        }
    }


    public void editUser(String oldUsername, String username, String email, String password, String accessibility) {
        String loggedInUsername = getLoggedInUsername();
        String currentLocation = "";

        if (loggedInUsername != null) {
            File userFile = new File(databaseDirectory + loggedInUsername + ".txt");
            List<String> lines = new ArrayList<>();
            boolean userFound = false;
            try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith(oldUsername + "|")) {

                        // Preserve the location information
                        String[] parts = line.split("\\|");
                        currentLocation = parts.length >= 5 ? parts[4] : ""; // Get the location
                        line = username + "|" + email + "|" + password + "|" + accessibility + "|" + currentLocation;

                        userFound = true;
                    }
                    lines.add(line);
                }
            } catch (IOException e) {
                handleFileError("Error editing user", e);
            }

            if (!userFound) {
                System.err.println("User not found in logged-in user file.");
                return;
            }

            // Write the updated content back to the logged-in user's file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                handleFileError("Error editing user", e);
            }

            // Update the user entry in Users.txt
            updateUserInUsersFile(oldUsername, username, email, password, accessibility, currentLocation);
        } else {
            System.err.println("No user is logged in.");
        }
    }

    private void updateUserInUsersFile(String oldUsername, String newUsername, String email, String password, String accessibility, String currentLocation) {
        List<String> updatedLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(usersFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(oldUsername + "|")) {
                    line = newUsername + "|" + email + "|" + password + "|" + accessibility + "|" + currentLocation;
                }
                updatedLines.add(line);
            }
        } catch (IOException e) {
            handleFileError("Error updating user in users file", e);
            return;
        }

        // Write the updated content back to the original file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(usersFile))) {
            for (String updatedLine : updatedLines) {
                writer.write(updatedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            handleFileError("Error updating users file", e);
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
