package zzbot;

/**
 * The {@code Parser} class provides methods to parse user input into commands and arguments.
 * It extracts the main command, splits arguments, and processes specific input formats for different types of tasks.
 */
public class Parser {

    /**
     * Parses the command from the user input string.
     * The command is assumed to be the first word in the input string.
     *
     * @param input The user input string.
     * @return The command as a string.
     */
    public String parseCommand(String input) {
        return input.split(" ")[0];
    }

    public String parseKeyword(String input) {
        return input.split(" ")[1];
    }

    /**
     * Parses the arguments from the user input string.
     * The method splits the input into two parts: the command and the remaining arguments.
     *
     * @param input The user input string.
     * @return An array of strings, where the first element is the command and the second element is the remaining arguments.
     */
    public String[] parseArguments(String input) {
        return input.split(" ", 2);
    }

    /**
     * Parses a number argument from the user input string.
     * This method assumes the number is the second word in the input string.
     *
     * @param input The user input string.
     * @return The parsed number as an integer.
     * @throws ZzBotParseCommandException If the input does not contain a valid number after the command.
     */
    public int parseNumberArgument(String input) throws ZzBotParseCommandException {
        try {
            return Integer.parseInt(input.split(" ")[1]);
        } catch (NumberFormatException e) {
            throw new ZzBotParseCommandException(input);
        }
    }

    /**
     * Parses the name of a ToDo task from the user input string.
     * The name is everything after the command.
     *
     * @param input The user input string.
     * @return The name of the ToDo task.
     * @throws ZzBotMissingArgumentException If the input does not contain any arguments after the command.
     */
    public String parseTodoName(String input) throws ZzBotMissingArgumentException {
        String command = parseCommand(input);
        if (input.length() <= command.length()) {
            throw new ZzBotMissingArgumentException();
        }
        return input.substring(command.length() + 1).trim();
    }

    /**
     * Parses the deadline task details from the user input string.
     * The input is expected to contain a command followed by a task description and a deadline,
     * separated by " /by ".
     *
     * @param input The user input string.
     * @return An array of strings, where the first element is the task description and the second element is the deadline.
     * @throws ZzBotParseCommandException If the input format is incorrect.
     */
    public String[] parseDeadline(String input) throws ZzBotParseCommandException {
        try {
            return input.substring(parseCommand(input).length() + 1).split(" /by ");
        } catch (Exception e) {
            throw new ZzBotParseCommandException(input);
        }
    }

    /**
     * Parses the event task details from the user input string.
     * The input is expected to contain a command followed by a task description, a start time, and an end time,
     * separated by " /from " and " /to ".
     *
     * @param input The user input string.
     * @return An array of strings, where the first element is the task description, the second element is the start time, and the third element is the end time.
     * @throws ZzBotParseCommandException If the input format is incorrect .
     */
    public String[] parseEvent(String input) throws ZzBotParseCommandException {
        try {
            return input.substring(parseCommand(input).length() + 1).split(" /from | /to ");
        } catch (Exception e) {
            throw new ZzBotParseCommandException(input);
        }
    }
}
