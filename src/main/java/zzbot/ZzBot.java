package zzbot;

import java.time.LocalDate;


/**
 * The {@code ZzBot} class represents the core functionality of the bot,
 * managing tasks and interacting with the user through a command-line interface.
 * It supports adding, deleting, listing, and finding tasks, as well as marking tasks
 * as done or not done. The bot also saves tasks to a storage file.
 */
public class ZzBot {

    private static String PERSONALITY_STRING = "Certainly Bro!\n";
    private static String MANUAL = "Certainly! Here is the manual:\nAvailable commands:\nadd\ndelete\nlist\nfind\nmark\nunmark\nbye\nTo add a new task, enter one of the following:\ndeadline\ntodo\nevent";
    private static String PATH = "./data/zzBot.txt";
    private String name;
    private TaskList taskList;
    private Ui ui;
    private Storage storage;


    /**
     * Constructs a {@code ZzBot} instance with a specified storage path.
     */
    public ZzBot() {
        this.name = "ZzBot";
        this.ui = new Ui();
        this.storage = new Storage(PATH);
        this.taskList = new TaskList(storage.load());

    }


    /**
     * Generates a greeting message for the user.
     *
     * @return A greeting message from ZzBot.
     */
    public String greet() {

        return String.format("\nHello! I'm %s", this.name) + "\nWhat can I do for you?\n";
    }

    /**
     * Generates a goodbye message for the user.
     *
     * @return A farewell message from ZzBot.
     */
    public String bye() {
        return personality() + "\nBye. Hope to see you again soon!\n";

    }

    /**
     * Adds personality to the bot by including a predefined personality string in responses.
     *
     * @return The personality string that ZzBot uses in its responses.
     */
    public String personality() {
        return PERSONALITY_STRING;
    }

    /**
     * Adds a task to the task list and generates a response acknowledging the addition.
     *
     * @param task The task to add.
     * @return A message confirming the task addition and the updated task count.
     */
    public String add(Task task) {
        this.taskList.add(task);
        String message = personality() + String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list",
                task.describe(), this.getNumOfTask());
        return message;
    }

    /**
     * Deletes a task from the task list based on the index and generates a response acknowledging the deletion.
     *
     * @param index The index of the task to delete (1-based).
     * @return A message confirming the task deletion and the updated task count.
     */
    public String delete(int index) {
        Task task = this.taskList.get(index - 1);
        this.taskList.delete(index - 1);
        String message = personality() + String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                task.describe(), this.getNumOfTask());
        return message;
    }

    /**
     * Lists all tasks in the task list and generates a response.
     *
     * @return A message listing all tasks in the task list.
     */
    public String list() {
        int length = this.taskList.size();
        String s = personality() + "Here are the tasks in your list:\n";
        for (int i = 0; i < length; i++) {
            Task task = this.taskList.get(i);
            String output = String.format("%d.%s", i + 1, task.describe());
            s += output + "\n";
        }
        return s;
    }

    /**
     * Finds and lists tasks that contain a specified keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A message listing all matching tasks.
     */
    public String find(String keyword) {
        String s = personality() + "Here are the matching tasks in your list:\n";
        int length = this.taskList.size();
        int cnt = 0;
        for (int i = 0; i < length; i++) {
            Task task = this.taskList.get(i);
            if (task.describe().contains(keyword)) {
                cnt += 1;
                String output = String.format("%d.%s", cnt, task.describe());
                s += output + "\n";
            }
        }
        return s;
    }

    /**
     * Marks a task as done and generates a response acknowledging the action.
     *
     * @param number The index of the task to mark as done (1-based).
     * @return A message confirming the task is marked as done.
     */
    public String mark(int number) {
        Task task = taskList.get(number - 1);
        task.markAsDone();
        String s = personality() + "Nice! I've marked this task as done:\n" + task.describe();
        return s;
    }

    /**
     * Unmarks a task as not done and generates a response acknowledging the action.
     *
     * @param number The index of the task to unmark as done (1-based).
     * @return A message confirming the task is marked as not done.
     */
    public String unmark(int number) {
        Task task = taskList.get(number - 1);
        task.markUnDone();
        String s = personality() + "OK, I've marked this task as not done yet:\n" + task.describe();
        return s;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getNumOfTask() {
        return this.taskList.size();
    }

    /**
     * Processes a user's input command and returns the corresponding response.
     *
     * @param input The user input to process.
     * @return The response after processing the input.
     * @throws ZzBotException If the command is invalid or cannot be processed.
     */
    public String process(String input) throws ZzBotException {
        Parser parser = new Parser();
        String command = parser.parseCommand(input);

        switch (command) {

        case "bye": {

            return this.ui.output(this.bye());
        }

        case "list": {
            return this.ui.output(this.list());
        }

        case "unmark": {
            int number = parser.parseNumberArgument(input);
            return this.ui.output(this.unmark(number));
        }

        case "mark": {
            int number = parser.parseNumberArgument(input);
            return this.ui.output(this.mark(number));

        }

        case "todo": {
            String name = parser.parseTodoName(input);
            Task task = new ToDos(name);
            return this.ui.output(this.add(task));
        }

        case "deadline": {
            String[] args = parser.parseDeadline(input);
            String name = args[0];
            String deadline = args[1].trim();

            LocalDate date = LocalDate.parse(deadline);

            Task task = new Deadlines(name, date);
            return this.ui.output(this.add(task));
        }

        case "event": {
            String[] args = parser.parseEvent(input);
            String name = args[0].trim();
            String start = args[1].trim();
            String end = args[2].trim();
            Task task = new Events(name, start, end);
            return this.ui.output(this.add(task));
        }

        case "delete": {
            int index = parser.parseNumberArgument(input);
            return this.ui.output(this.delete(index));
        }

        case "find": {
            String keyword = parser.parseKeyword(input);
            return this.ui.output(this.find(keyword));

        }

        case "help": {
            return this.ui.output(MANUAL);
        }

        default: {
            throw new ZzBotInvalidCommandException(command);
        }
        }
    }


    /**
     * Processes the user input and generates a response.
     * <p>
     * This method takes the user input, processes it using the {@code process} method,
     * and returns the corresponding response. If the response is "bye", the program will exit.
     * Additionally, it saves the current task list to storage after processing the input.
     * <p>
     * If an exception is thrown during processing, the method catches it and returns
     * the exception message as the response.
     *
     * @param input The user input to process.
     * @return The response after processing the input. If an exception occurs, the exception message is returned.
     */
    public String getResponse(String input) {
        try {
            String response = this.process(input);
            if (response.equals("bye")) {
                System.exit(0);
            }
            this.storage.record(this.taskList);
            return response;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
