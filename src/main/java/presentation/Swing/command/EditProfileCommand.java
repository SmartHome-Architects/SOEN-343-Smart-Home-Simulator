package presentation.Swing.command;

public class EditProfileCommand implements Command {
    private UserAccountManager accountManager;
    private String oldUsername;
    private String username;
    private String password;
    private String email;
    private String accessibility;

    public EditProfileCommand(UserAccountManager accountManager, String oldUsername, String username, String email, String password, String accessibility) {
        this.accountManager = accountManager;
        this.oldUsername = oldUsername;
        this.username = username;
        this.email = email;
        this.password = password;
        this.accessibility = accessibility;
    }

    @Override
    public void execute() {
        accountManager.editUser(oldUsername, username, email, password, accessibility);
    }
}
