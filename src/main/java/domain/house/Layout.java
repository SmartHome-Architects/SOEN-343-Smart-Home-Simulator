package domain.house;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.sensors.*;

public class Layout {

    // This function loads the json layout file using helper functions and returns a list of room objects.
    public static List<Room> loadLayout(){
        List<Room> rooms = new ArrayList<>(); //list of room objects
        String layoutFilePath = "src/main/java/domain/house/house_layout_sample.json"; //temp location
        ObjectMapper jsonMapper = new ObjectMapper(); // json parser (JSON -> object)

        try {
            File file = new File(layoutFilePath);

            JsonNode rootNode = jsonMapper.readTree(file);
            JsonNode roomsNode = rootNode.path("rooms");

            int id = 1;
            for (JsonNode roomNode : roomsNode) {
                String roomName = roomNode.path("name").asText();
                AC acUnit = new AC(id);
                Heater heater = new Heater(id);
                double temperature = 20.0;

                Coordinate roomCoordinates = createCoordinates(roomNode.path("coordinates"));
                Door door = createDoor(roomNode.path("door"),id,roomName);
                List<Window> windows = createWindowList(roomNode.path("windows"),roomName);
                List<Light> lights = createLightList(roomNode.path("lights"),roomName);

                Room room = new Room(id, roomName, door, lights, windows, acUnit, heater, temperature,roomCoordinates);
                id++;
                rooms.add(room);
            }}catch(IOException e) {
                e.printStackTrace();
            }
        return rooms;
    }

    private static Coordinate createCoordinates(JsonNode node){
        return new Coordinate(node.path("x").asInt(),node.path("y").asInt());
    }

    private static Door createDoor(JsonNode node,int id, String roomName){
        if (!node.isMissingNode() && node.isArray()) {
            JsonNode door = node.get(0);
            Coordinate doorCoordinates = createCoordinates(door.path("coordinates"));
            return new Door("Door", roomName, id, doorCoordinates);
        }
        else{
            return null;
        }
    }

    private static List<Window> createWindowList(JsonNode node, String roomName) {
        ArrayList<Window> list = new ArrayList<>();
        int id = 1;
        for (JsonNode compNode : node) {
            Coordinate windowCoordinates = createCoordinates(compNode.path("coordinates"));
            Window w = new Window("Window", roomName, id, windowCoordinates);
            id++;
            list.add(w);
        }
        return list;
    }
    private static List<Light> createLightList(JsonNode node, String roomName) {
        ArrayList<Light> list = new ArrayList<>();
        int id = 1;
        for(JsonNode compNode:node){
            Coordinate lightCoordinates = createCoordinates(compNode.path("coordinates"));
            Light l = new Light("Light", roomName,id,lightCoordinates);
            id++;
            list.add(l);
        }
        return list;
    }

}