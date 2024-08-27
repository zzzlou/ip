package zzbot;
public class ZzBotInvalidCommandException extends ZzBotException{
    public ZzBotInvalidCommandException(String command) {
        super("Invalid command: " + command);
    }
}
