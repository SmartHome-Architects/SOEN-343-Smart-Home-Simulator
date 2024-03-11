package src.main.java.presentation.Swing.command;

public class DeleteProfileCommand implements Command{
    private UserAccountManager accountManager;
    private String username;

    public DeleteProfileCommand(UserAccountManager accountManager, String username) {
        this.accountManager = accountManager;
        this.username = username;
    }

    @Override
    public void execute() {
        accountManager.deleteUser(username);
    }
}
