package presentation.Swing.SHH;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class RoomSerializer {
    private int roomId;
    private String roomName;
    private double desiredRoomTemperature;


// This class reads and updates JSON file
    @JsonCreator
    public RoomSerializer(@JsonProperty("roomId") int roomId,
                          @JsonProperty("roomName") String roomName,
                          @JsonProperty("desiredRoomTemperature") double desiredRoomTemperature) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.desiredRoomTemperature = desiredRoomTemperature;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public double getDesiredRoomTemperature() {
        return desiredRoomTemperature;
    }

    public void setDesiredRoomTemperature(double desiredRoomTemperature) {
        this.desiredRoomTemperature = desiredRoomTemperature;
    }

    // Serialize room data to JSON file
    public static void saveRoomInfo(List<RoomSerializer> roomSerializerList, String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(filePath), roomSerializerList);
            System.out.println("Room information saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving room information: " + e.getMessage());
        }
    }

    // Deserialize to display info on popup
    public static List<RoomSerializer> loadRoomInfo(String filename) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filename);

        try {
            return mapper.readValue(file, new TypeReference<List<RoomSerializer>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
