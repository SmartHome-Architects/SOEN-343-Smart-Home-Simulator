package presentation.Swing.command;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserAccountManager {
    private File Users;

    public UserAccountManager(String filePath) {
        this.Users = new File(filePath);
    }

    public List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Users))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|"); // Escape the pipe character
                usernames.add(parts[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usernames;
    }

    public void addUser(String username, String email, String password, String accessibility) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Users, true))) {
            writer.write(username + "|" + email + "|" + password + "|" + accessibility);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String username) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Users))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(username + "|")) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Users))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editUser(String oldUsername, String username, String email, String password, String accessibility) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Users))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(oldUsername + "|")) {
                    line = username + "|" + email + "|" + password + "|" + accessibility;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Users))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
