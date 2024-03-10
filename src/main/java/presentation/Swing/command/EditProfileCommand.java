package src.main.java.presentation.Swing.command;

public class EditProfileCommand implements Command{
    private UserAccountManager accountManager;
    private String username;
    private String newProfileData;

    public EditProfileCommand(UserAccountManager accountManager, String username, String newProfileData) {
        this.accountManager = accountManager;
        this.username = username;
        this.newProfileData = newProfileData;
    }

    @Override
    public void execute() {
        accountManager.editUser(username, newProfileData);
    }
}
