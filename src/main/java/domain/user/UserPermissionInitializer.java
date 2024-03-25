package domain.user;

import domain.Permission.Permission;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserPermissionInitializer {
    public static Permission initializePermission(String doorPermissionFile, String lightPermissionFile, String windowPermissionFile, String shhPermissionFile, String userType) {
        boolean hasDoorPermissionOutside = false;
        boolean hasDoorPermissionInsideHome = false;
        boolean hasLightPermissionOutside = false;
        boolean hasLightPermissionInsideHome = false;
        boolean hasWindowPermissionOutside = false;
        boolean hasWindowPermissionInsideHome = false;
        boolean hasHeaterPermissionOutside = false;
        boolean hasHeaterPermissionInsideHome = false;

        try {
            Scanner doorScanner = new Scanner(new File(doorPermissionFile));
            Scanner lightScanner = new Scanner(new File(lightPermissionFile));
            Scanner windowScanner = new Scanner(new File(windowPermissionFile));
            Scanner shhScanner = new Scanner(new File(shhPermissionFile));

            while (doorScanner.hasNextLine()) {
                String line = doorScanner.nextLine();
                String[] data = line.split("\\|");
                if (data[0].equals(userType)) {
                    hasDoorPermissionOutside = Boolean.parseBoolean(data[1]);
                    hasDoorPermissionInsideHome = Boolean.parseBoolean(data[2]);
                    break;
                }
            }

            while (lightScanner.hasNextLine()) {
                String line = lightScanner.nextLine();
                String[] data = line.split("\\|");
                if (data[0].equals(userType)) {
                    hasLightPermissionOutside = Boolean.parseBoolean(data[1]);
                    hasLightPermissionInsideHome = Boolean.parseBoolean(data[2]);
                    break;
                }
            }

            while (windowScanner.hasNextLine()) {
                String line = windowScanner.nextLine();
                String[] data = line.split("\\|");
                if (data[0].equals(userType)) {
                    hasWindowPermissionOutside = Boolean.parseBoolean(data[1]);
                    hasWindowPermissionInsideHome = Boolean.parseBoolean(data[2]);
                    break;
                }
            }

            while (shhScanner.hasNextLine()) {
                String line = shhScanner.nextLine();
                String[] data = line.split("\\|");
                if (data[0].equals(userType)) {
                    hasHeaterPermissionOutside = Boolean.parseBoolean(data[1]);
                    hasHeaterPermissionInsideHome = Boolean.parseBoolean(data[2]);
                    break;
                }
            }

            doorScanner.close();
            lightScanner.close();
            windowScanner.close();
            shhScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new Permission(hasWindowPermissionOutside, hasWindowPermissionInsideHome, hasDoorPermissionOutside, hasDoorPermissionInsideHome, hasLightPermissionOutside, hasLightPermissionInsideHome, hasHeaterPermissionOutside, hasHeaterPermissionInsideHome);
    }
}
