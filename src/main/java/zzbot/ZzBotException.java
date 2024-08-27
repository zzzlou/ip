package zzbot;
public class ZzBotException extends Exception{

    public ZzBotException(String message) {
        super("Oops, Duke did not work as expected because: " + message);
    }
}
