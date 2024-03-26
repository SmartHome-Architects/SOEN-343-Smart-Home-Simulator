import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import domain.dateTime.Date;
import org.junit.Test;

public class DateTest {

    @Test
    public void testDefaultConstructor() {
        Date date = new Date();
        assertNotNull(date);
        assertNotNull(date.getDayOfWeek());
        assertNotNull(date.getDay());
        assertNotNull(date.getMonth());
        assertNotNull(date.getYear());
    }

    @Test
    public void testParameterizedConstructor() {
        Date date = new Date(25, 3, 2024, "Thursday");
        assertNotNull(date);
        assertEquals(25, date.getDay());
        assertEquals(3, date.getMonth());
        assertEquals(2024, date.getYear());
        assertEquals("Thursday", date.getDayOfWeek());
    }

    @Test
    public void testStringConstructor() {
        Date date = new Date("Thursday, 25/03/2024");
        assertNotNull(date);
        assertEquals(25, date.getDay());
        assertEquals(3, date.getMonth());
        assertEquals(2024, date.getYear());
        assertEquals("Thursday", date.getDayOfWeek());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testInvalidStringConstructor() {
        // This should throw an exception since the string format is invalid
        Date date = new Date("Invalid Date String");
    }

    @Test
    public void testToString() {
        Date date = new Date(25, 3, 2024, "Thursday");
        assertEquals("Thursday, 25/03/2024", date.toString());
    }
}
