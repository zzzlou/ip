package zzbot;

/**
 * Exception thrown when a command fails to be parsed in the ZzBot application.
 * This exception extends {@link ZzBotException} and provides a detailed message
 * including the command that failed to parse.
 *
 * <p>This is typically thrown when the system encounters a command input that
 * does not meet the expected format or contains invalid data.
 *
 * @see ZzBotException
 */
public class ZzBotParseCommandException extends ZzBotException {

    public ZzBotParseCommandException(String command) {
        super("Failed to parse the following command: " + command);
    }
}
