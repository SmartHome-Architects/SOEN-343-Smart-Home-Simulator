package presentation.Swing.SHH;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.house.Room;
import domain.house.Zone;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZoneNameSerializer {

    private static final String JSON_FILE_PATH = "zones.json";

    public static void saveZones(List<Zone> zones) {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> existingZones = loadExistingZones();

        // Append new zones to the existing list
        existingZones.addAll(convertZonesToMapList(zones));

        try {
            // Serialize the combined list of zones to the JSON file
            mapper.writeValue(new File(JSON_FILE_PATH), existingZones);
            System.out.println("Zones saved to " + JSON_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error saving zones: " + e.getMessage());
        }
    }

    private static List<Map<String, Object>> loadExistingZones() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Deserialize the existing zones from the JSON file
            return mapper.readValue(new File(JSON_FILE_PATH),
                    new TypeReference<List<Map<String, Object>>>() {});
        } catch (IOException e) {
            System.err.println("Error loading existing zones: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static List<Map<String, Object>> convertZonesToMapList(List<Zone> zones) {
        List<Map<String, Object>> zoneList = new ArrayList<>();
        for (Zone zone : zones) {
            Map<String, Object> zoneMap = Map.of(
                    "zoneName", zone.getZoneName(),
                    "desiredTemperature", zone.getDesiredZoneTemperature(), // Add desired temperature
                    "rooms", convertRoomsToMapList(zone.getZoneRooms())
            );
            zoneList.add(zoneMap);
        }
        return zoneList;
    }

    static List<Map<String, Object>> convertRoomsToMapList(List<Room> rooms) {
        List<Map<String, Object>> roomList = new ArrayList<>();
        for (Room room : rooms) {
            Map<String, Object> roomMap = Map.of(
                    "roomId", room.getId(),
                    "roomName", room.getRoomName()
            );
            roomList.add(roomMap);
        }
        return roomList;
    }

    public static void loadAndPrintZones() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Deserialize the zones from the JSON file
            List<Map<String, Object>> zoneList = mapper.readValue(new File(JSON_FILE_PATH),
                    new TypeReference<List<Map<String, Object>>>() {});

            // Output the zones in the console
            System.out.println("Zones loaded from " + JSON_FILE_PATH);
            for (Map<String, Object> zoneMap : zoneList) {
                String zoneName = (String) zoneMap.get("zoneName");
                double desiredTemperature = (double) zoneMap.get("desiredTemperature"); // Load desired temperature
                System.out.println("Zone Name: " + zoneName);
                System.out.println("Desired Temperature: " + desiredTemperature); // Display desired temperature
                List<Map<String, Object>> roomList = (List<Map<String, Object>>) zoneMap.get("rooms");
                for (Map<String, Object> roomMap : roomList) {
                    int roomId = (int) roomMap.get("roomId");
                    String roomName = (String) roomMap.get("roomName");
                    System.out.println("Room ID: " + roomId + ", Room Name: " + roomName);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading zones: " + e.getMessage());
        }
    }
}
