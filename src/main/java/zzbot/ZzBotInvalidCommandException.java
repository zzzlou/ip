package zzbot;

/**
 * Exception thrown when an invalid command is provided.
 */
public class ZzBotInvalidCommandException extends ZzBotException {

    public ZzBotInvalidCommandException(String command) {
        super("Invalid command: " + command);
    }
}
