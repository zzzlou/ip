package zzbot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlinesTest {

    private Deadlines deadline1;
    private Deadlines deadline2;

    @BeforeEach
    void setUp() {
        // Initialize Deadlines instances for testing
        deadline1 = new Deadlines("Submit assignment", LocalDate.of(2024, 9, 30));
        deadline2 = new Deadlines("Submit project", true, LocalDate.of(2024, 10, 15));
    }

    @Test
    void testDescribe() {
        // Expected output for the describe method
        String expectedDescription1 = "[D][ ] Submit assignment (by: Sep 30 2024)";
        String expectedDescription2 = "[D][X] Submit project (by: Oct 15 2024)";

        // Test the describe method
        assertEquals(expectedDescription1, deadline1.describe(), "Description should match the expected format for incomplete task");
        assertEquals(expectedDescription2, deadline2.describe(), "Description should match the expected format for completed task");
    }
}
