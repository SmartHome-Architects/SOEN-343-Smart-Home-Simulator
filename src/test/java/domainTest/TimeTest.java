package domainTest;

import domain.dateTime.Time;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimeTest {

    @Test
    public void testConstructorWithTimeString() {
        // Define a test time string
        String timeString = "12:34:56";

        // Create a new Time object using the constructor that accepts a time string
        Time time = new Time(timeString);

        // Verify that the time object has been constructed correctly
        assertEquals(12, time.getHour());
        assertEquals(34, time.getMinute());
        assertEquals(56, time.getSecond());
    }

    @Test
    public void testIncrement() {
        // Create a new Time object with specific values
        Time time = new Time(23, 59, 59);

        // Increment the time
        time.increment();

        // Verify that the time has been incremented correctly
        assertEquals(0, time.getHour());
        assertEquals(0, time.getMinute());
        assertEquals(0, time.getSecond());
    }

    @Test
    public void testToString() {
        // Create a new Time object with specific values
        Time time = new Time(8, 5, 30);

        // Verify that the toString method returns the expected string representation
        assertEquals("08:05:30", time.toString());
    }


}
