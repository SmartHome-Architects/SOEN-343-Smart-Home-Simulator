package domain.house;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Layout {

    public static List<Room> loadLayout() {
        List<Room> rooms = new ArrayList<>();
        String layoutFilePath = "house_layout_sample.json";
        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File(layoutFilePath);

        if(file.exists()){

        }

        return rooms;
    }
}
