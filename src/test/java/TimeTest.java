import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import domain.dateTime.Time;
import org.junit.Test;

public class TimeTest {

    @Test
    public void testDefaultConstructor() {
        Time time = new Time();
        assertNotNull(time);
        assertNotNull(time.getHour());
        assertNotNull(time.getMinute());
        assertNotNull(time.getSecond());
    }

    @Test
    public void testParameterizedConstructor() {
        Time time = new Time(10, 30, 45);
        assertNotNull(time);
        assertEquals(10, time.getHour());
        assertEquals(30, time.getMinute());
        assertEquals(45, time.getSecond());
    }

    @Test
    public void testStringConstructor() {
        Time time = new Time("15:20:30");
        assertNotNull(time);
        assertEquals(15, time.getHour());
        assertEquals(20, time.getMinute());
        assertEquals(30, time.getSecond());
    }

    @Test(expected = NumberFormatException.class)
    public void testInvalidStringConstructor() {
        // This should throw an exception since the string format is invalid
        Time time = new Time("Invalid Time String");
    }

    @Test
    public void testIncrement() {
        Time time = new Time(23, 59, 59);
        time.increment();
        assertEquals("00:00:00", time.toString());
    }

    @Test
    public void testToString() {
        Time time = new Time(9, 15, 30);
        assertEquals("09:15:30", time.toString());
    }
}
