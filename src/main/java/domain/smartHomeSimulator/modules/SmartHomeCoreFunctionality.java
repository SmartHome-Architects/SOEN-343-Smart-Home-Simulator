package domain.smartHomeSimulator.modules;


import domain.Permission.Permission;
import domain.Permission.WindowPermission;
import domain.sensors.Window;
import domain.user.Profile;

import java.util.List;

public class SmartHomeCoreFunctionality {
    private Profile user;

    public SmartHomeCoreFunctionality(Profile user) {
        this.user = user;
    }

    public Profile getUser() {
        return user;
    }

    public void setUser(Profile user) {
        this.user = user;
    }

    public void openWindow(Window window) {
        if (checkPermission(window, "open")) {
            window.setOpen(true);
            System.out.println("Window opened successfully.");
        } else {
            System.out.println("You do not have permission to open this window.");
        }
    }

    // Method to close a window based on user's permissions.txt
    public void closeWindow(Window window) {
        if (checkPermission(window, "close")) {
            window.setOpen(false);
            System.out.println("Window closed successfully.");
        } else {
            System.out.println("You do not have permission to close this window.");
        }
    }


    private boolean checkPermission(Window window, String action) {
        for (Permission permission : user.getPermissions()) {
            if (permission instanceof WindowPermission) {
                WindowPermission windowPermission = (WindowPermission) permission;
                if (windowPermission.isHasWindowPermission()) {
                    
                    return true;
                }
            }
        }
        return false;
    }


    // Method to grant window permission to a user
    public void grantWindowPermission(Profile user) {
        List<Permission> permissions = user.getPermissions();
        permissions.add(new WindowPermission(true));
        user.setPermissions(permissions);
        System.out.println("Window permission granted to user: " + user.getUsername());
    }

    // Method to deny window permission to a user
    public void denyWindowPermission(Profile user) {
        List<Permission> permissions = user.getPermissions();
        permissions.removeIf(permission -> permission instanceof WindowPermission);
        user.setPermissions(permissions);
        System.out.println("Window permission denied to user: " + user.getUsername());
    }



    @Override
    public String toString() {
        return "SmartHomeCoreFunctionality{" +
                "user=" + user +
                '}';
    }
}
