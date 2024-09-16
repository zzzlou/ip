package zzbot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ParserTest {

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
