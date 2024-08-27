public class Parser {

    public String parseCommand(String input) {
        return input.split(" ")[0];
    }

    public String[] parseArguments(String input) {
        return input.split(" ", 2);
    }

    public int parseNumberArgument(String input) throws ZzBotParseCommandException {
        try {
            return Integer.parseInt(input.split(" ")[1]);
        } catch (NumberFormatException e) {
            throw new ZzBotParseCommandException(input);
        }
    }

    public String parseTodoName(String input) throws ZzBotMissingArgumentException {
        String command = parseCommand(input);
        if (input.length() <= command.length()) {
            throw new ZzBotMissingArgumentException();
        }
        return input.substring(command.length() + 1).trim();
    }

    public String[] parseDeadline(String input) throws ZzBotParseCommandException {
        try {
            return input.substring(parseCommand(input).length() + 1).split(" /by ");
        } catch (Exception e) {
            throw new ZzBotParseCommandException(input);
        }
    }

    public String[] parseEvent(String input) throws ZzBotParseCommandException {
        try {
            return input.substring(parseCommand(input).length() + 1).split(" /from | /to ");
        } catch (Exception e) {
            throw new ZzBotParseCommandException(input);
        }
    }
}

