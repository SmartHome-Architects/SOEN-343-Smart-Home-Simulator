package domain.house;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.sensors.*;

public class Layout {

    public static List<Room> loadLayout(){
        List<Room> rooms = new ArrayList<>();
        String layoutFilePath = "src/main/java/domain/house/house_layout_sample.json";
        ObjectMapper jsonMapper = new ObjectMapper();

        try {
            File file = new File(layoutFilePath);

            JsonNode rootNode = jsonMapper.readTree(file);
            JsonNode roomsNode = rootNode.path("rooms");


            int id = 1;
            for (JsonNode roomNode : roomsNode) {
                String name = roomNode.path("name").asText();
                AC acUnit = new AC(id);
                Heater heater = new Heater(id);
                double temperature = 20.0;

                //add function to loop over room components

                Door door = new Door("Door", name, id);

                Room room = new Room(0, name, door, null, null, acUnit, heater, temperature);
                id++;
                rooms.add(room);
            }
            }catch(IOException e) {
                e.printStackTrace();
            }
            return rooms;
    }
}