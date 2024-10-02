package zzbot;

/**
 * Exception thrown when a required argument is missing.
 */
public class ZzBotMissingArgumentException extends ZzBotException {

    public ZzBotMissingArgumentException() {
        super("Missing argument");
    }
}
