package zzbot;

public class ZzBotInvalidDateException extends ZzBotException{

    public ZzBotInvalidDateException(String command) {
        super("Invalid date: " + command);
    }
}
