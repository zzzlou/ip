package zzbot;

public class ZzBotException extends Exception {

    public ZzBotException(String message) {
        super("Oops, Duke did not work as expected " + message + "\nEnter 'help' for manual");
    }
}
