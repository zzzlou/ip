package zzbot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit test for the {@link Parser} class.
 * This test suite checks the parsing functionality of deadlines
 * to ensure valid input is correctly processed without exceptions.
 */
public class ParserTest {

    /**
     * Tests the {@link Parser#parseDeadline(String)} method with valid input.
     * <p>
     * The test checks if the input string "return book /by 2023-12-31" is properly parsed into a task description
     * and deadline date.
     * </p>
     * <p>
     * The test expects no exception to be thrown for valid input and asserts that the parsed
     * task description and deadline match the expected values.
     * </p>
     */
    @Test
    public void testParseDeadline_validInput_success() {
        Parser parser = new Parser();
        String input = "return book /by 2023-12-31";

        try {
            String[] result = parser.parseDeadline(input);
            assertEquals("book", result[0], "The task description should be 'return book'");
            assertEquals("2023-12-31", result[1], "The deadline should be '2023-12-31'");
        } catch (ZzBotInvalidDateException e) {
            fail("Exception should not be thrown for valid input");
        }
    }
}
