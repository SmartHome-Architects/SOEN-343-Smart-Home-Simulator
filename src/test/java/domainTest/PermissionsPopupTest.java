package domainTest;

import org.junit.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.junit.Assert.assertEquals;

public class PermissionsPopupTest {

    @Test
    public void testUpdateTableModel() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"User Type", "Outside Permission", "Inside Permission"}, 0);
        PermissionsPopup permissionsPopup = new PermissionsPopup();

        // Get the updateTableModel method using reflection
        Method updateTableModelMethod = PermissionsPopup.class.getDeclaredMethod("updateTableModel", String.class, DefaultTableModel.class, String.class, JTextArea.class);
        updateTableModelMethod.setAccessible(true); // Set accessible since the method is private

        // Create dummy arguments for user and textArea1
        String dummyUser = "dummyUser";
        JTextArea dummyTextArea = new JTextArea();

        // Invoke the method with the desired parameters
        updateTableModelMethod.invoke(permissionsPopup, "Door", model, dummyUser, dummyTextArea);

        // Assert the number of rows added to the table model
        assertEquals(3, model.getRowCount());

        // Assert the values in the first row
        assertEquals("Parent", model.getValueAt(0, 0));
        assertEquals(true, model.getValueAt(0, 1));
        assertEquals(true, model.getValueAt(0, 2));

        // Assert the values in the second row
        assertEquals("Children", model.getValueAt(1, 0));
        assertEquals(true, model.getValueAt(1, 1));
        assertEquals(true, model.getValueAt(1, 2));

        // Assert the values in the third row
        assertEquals("Guest", model.getValueAt(2, 0));
        assertEquals(true, model.getValueAt(2, 1));
        assertEquals(true, model.getValueAt(2, 2));
    }
}
