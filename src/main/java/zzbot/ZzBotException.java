package zzbot;

/**
 * Base exception class for all ZzBot-related errors.
 */
public class ZzBotException extends Exception {

    public ZzBotException(String message) {
        super("Oops, ZzBot did not work as expected: " + message + "\nEnter 'help' for manual");
    }
}
