import domain.editbutton.EditHouseInhabitantsDialog;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EditHouseInhabitantsDialogTest {


    @Test
    public void testSaveChangesOutside() {
        // Create an instance of EditHouseInhabitantsDialog
        EditHouseInhabitantsDialog dialog = new EditHouseInhabitantsDialog(null);

        // Set the inhabitant and location
        dialog.inhabitantComboBox.setSelectedItem("Inhabitant 2");
        dialog.locationComboBox.setSelectedItem("Outside");

        // Save changes
        dialog.saveChanges();

        // Check if the location is updated correctly
        assertEquals("Outside", dialog.locationComboBox.getSelectedItem());
    }
}
