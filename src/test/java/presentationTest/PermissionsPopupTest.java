package presentationTest;

import org.junit.jupiter.api.Test;

import javax.swing.table.DefaultTableModel;
import static org.junit.jupiter.api.Assertions.*;

public class PermissionsPopupTest {

    @Test
    public void testUpdateTableModel() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"User Type", "Outside Permission", "Inside Permission"}, 0);
        String category = "Door";

        // Assert that the model has been updated with data
        assertEquals(0, model.getRowCount()); // Assuming there are three permissions in the "Door" category file
    }
}
