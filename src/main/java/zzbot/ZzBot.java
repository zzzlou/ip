package zzbot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ZzBot {

    private String name;
    private String line;
    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private static String PATH = "./data/zzBot.txt";
    private static String MANUAL = "Certainly! Here is the manual:\nAvailable commands:\nadd\ndelete\nlist\nfind\nmark\nunmark\nbye\nTo add a new task, enter one of the following:\ndeadline\ntodo\nevent";
    public static String PERSONALITY_STRING = "Certainly Bro!\n";
    public ZzBot(String path) {
        this.name = "zzBot";
        this.ui = new Ui();
        this.storage = new Storage(path);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    public ZzBot() {
        this(PATH);
    }

    public String greet() {

        return String.format("\nHello! I'm %s", this.name) +
                "\nWhat can I do for you?\n";
    }

    public String bye() {
        return personality() + "\nBye. Hope to see you again soon!\n";
    }

    public String personality() {
        return PERSONALITY_STRING;
    }

    public String add(Task task) {
        this.taskList.add(task);
        String message = personality() + String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list",
                task.describe(), this.getNumOfTask());
        return message;
    }

    public String delete(int index) {
        Task task = this.taskList.get(index - 1);
        this.taskList.delete(index - 1);
        String message = personality() + String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                task.describe(), this.getNumOfTask());
        return message;
    }

    public String list() {
        int length = this.taskList.size();
        String s = personality() + "Here are the tasks in your list:\n";
        for (int i = 0; i < length; i++) {
            Task task = this.taskList.get(i);
            String output = personality() + String.format("%d.%s", i + 1, task.describe());
            s += output + "\n";
        }
        return s;
    }

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

    public String mark(int number) {
        Task task = taskList.get(number - 1);
        task.markAsDone();
        String s = personality() + "Nice! I've marked this task as done:\n" + task.describe();
        return s;
    }

    public String unmark(int number) {
        Task task = taskList.get(number - 1);
        task.markUnDone();
        String s = personality() + "OK, I've marked this task as not done yet:\n" + task.describe();
        return s;
    }

    public int getNumOfTask() {
        return this.taskList.size();
    }

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
                System.out.println(Arrays.toString(args));
                String deadline = args[1].trim();
                LocalDate date;
                try {
                    date = LocalDate.parse(deadline);
                } catch (Exception e) {
                    throw new ZzBotInvalidDateException(deadline);
                }
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
                String manual = "manual";
                return this.ui.output(MANUAL);
            }

            default: {
                throw new ZzBotInvalidCommandException(command);
            }
        }
    }


    /**
     * Processes the user input and generates a response.
     *
     * This method takes the user input, processes it using the {@code process} method,
     * and returns the corresponding response. If the response is "bye", the program will exit.
     * Additionally, it saves the current task list to storage after processing the input.
     *
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
