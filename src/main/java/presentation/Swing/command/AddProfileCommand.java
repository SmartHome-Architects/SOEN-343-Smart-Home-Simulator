package src.main.java.presentation.Swing.command;

public class AddProfileCommand implements Command{
    private UserAccountManager accountManager;
    private String username;
    private String password;
    private String email;
    private String accessibility;


    public AddProfileCommand(UserAccountManager accountManager, String username, String email, String password, String accessibility) {
        this.accountManager = accountManager;
        this.username = username;
        this.email = email;
        this.password = password;
        this.accessibility = accessibility;
    }

    @Override
    public void execute() {
        accountManager.addUser(username, email, password, accessibility);
    }
}
