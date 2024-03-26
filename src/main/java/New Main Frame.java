public class MainFrame {

    // Existing code...

    private void initializeLightCheckboxes() {
        // Assuming these are your light checkboxes
        JCheckBox kitchenLightCheckBox = new JCheckBox("Kitchen Light");
        JCheckBox livingRoomLightCheckBox = new JCheckBox("Living Room Light");
        JCheckBox bedroom1LightCheckBox = new JCheckBox("Bedroom 1 Light");
        JCheckBox bedroom2LightCheckBox = new JCheckBox("Bedroom 2 Light");
        JCheckBox bathroomLightCheckBox = new JCheckBox("Bathroom Light");
        JCheckBox hallwayLightCheckBox = new JCheckBox("Hallway Light");
        JCheckBox garageLightCheckBox = new JCheckBox("Garage Light");
        JCheckBox frontLightCheckBox = new JCheckBox("Front Light");
        JCheckBox backLightCheckBox = new JCheckBox("Back Light");

        // Adding action listeners for each light checkbox
        kitchenLightCheckBox.addActionListener(new LightCheckBoxListener());
        livingRoomLightCheckBox.addActionListener(new LightCheckBoxListener());
        bedroom1LightCheckBox.addActionListener(new LightCheckBoxListener());
        bedroom2LightCheckBox.addActionListener(new LightCheckBoxListener());
        bathroomLightCheckBox.addActionListener(new LightCheckBoxListener());
        hallwayLightCheckBox.addActionListener(new LightCheckBoxListener());
        garageLightCheckBox.addActionListener(new LightCheckBoxListener());
        frontLightCheckBox.addActionListener(new LightCheckBoxListener());
        backLightCheckBox.addActionListener(new LightCheckBoxListener());

        // Add the checkboxes to your GUI panels or containers
        // For example:
        kitchenLightPanel.add(kitchenLightCheckBox);
        livingRoomLightPanel.add(livingRoomLightCheckBox);
        // Add other checkboxes to respective panels similarly...
    }

   
