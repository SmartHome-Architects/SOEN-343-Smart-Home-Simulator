package domainTest;

import domain.dateTime.Date;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DateTest {

    @Test
    public void testConstructorWithDateString() {
        // Define a test date string
        String dateString = "MONDAY, 27/03/2023";

        // Create a new Date object using the constructor that accepts a date string
        Date date = new Date(dateString);

        // Verify that the date object has been constructed correctly
        assertEquals(27, date.getDay());
        assertEquals(3, date.getMonth());
        assertEquals(2023, date.getYear());
        assertEquals("MONDAY", date.getDayOfWeek());
    }

    @Test
    public void testToString() {
        // Create a new Date object with specific values
        Date date = new Date(27, 3, 2023, "MONDAY");

        // Verify that the toString method returns the expected string representation
        assertEquals("MONDAY, 27/03/2023", date.toString());
    }


}
