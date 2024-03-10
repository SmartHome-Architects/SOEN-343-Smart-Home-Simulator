package src.main.java.presentation.Swing.command;

public class ProfileManager {
    private Command addProfileCommand;
    private Command deleteProfileCommand;
    private Command editProfileCommand;

    public ProfileManager(Command addProfileCommand, Command deleteProfileCommand, Command editProfileCommand) {
        this.addProfileCommand = addProfileCommand;
        this.deleteProfileCommand = deleteProfileCommand;
        this.editProfileCommand = editProfileCommand;
    }

    public void addProfile() {
        addProfileCommand.execute();
    }

    public void deleteProfile() {
        deleteProfileCommand.execute();
    }

    public void editProfile() {
        editProfileCommand.execute();
    }
}
