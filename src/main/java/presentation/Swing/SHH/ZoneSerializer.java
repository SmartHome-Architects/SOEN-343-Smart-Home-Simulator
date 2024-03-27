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

public class ZoneSerializer {

    private static final String JSON_FILE_PATH = "database/zones.json";

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
}
