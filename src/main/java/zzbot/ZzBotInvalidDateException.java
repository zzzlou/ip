package zzbot;

/**
 * Exception thrown when an invalid date is encountered in a command.
 */
public class ZzBotInvalidDateException extends ZzBotException {

    public ZzBotInvalidDateException(String command) {
        super("Invalid date: " + command);
    }
}
