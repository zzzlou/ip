package zzbot;
public class ZzBotParseCommandException extends ZzBotException{

    public ZzBotParseCommandException(String command) {
        super("Failed to parse the following command: " + command);
    }
}
