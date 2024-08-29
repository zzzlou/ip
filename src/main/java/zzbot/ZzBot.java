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

    public ZzBot(String path) {
        this.name = "zzBot";
        this.line = "____________________________________________________________";
        this.ui = new Ui();
        this.storage = new Storage(path);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    public String greet() {

        String message = this.line + String.format("\nHello! I'm %s", this.name) +
                "\nWhat can I do for you?\n" + this.line;

        return message;
    }

    public String bye() {
        String s = this.line + "\nBye. Hope to see you again soon!\n" + this.line;
        return s;
    }

    public String echo(String input) {
        return input;
    }

    public String add(Task task) {
        this.taskList.add(task);
        String message = String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list",
                task.describe(), this.getNumOfTask());
        return message;
    }

    public String delete(int index) {
        Task task = this.taskList.get(index - 1);
        this.taskList.delete(index - 1);
        String message = String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                task.describe(), this.getNumOfTask());
        return message;
    }

    public String list() {
        int length = this.taskList.size();
        String s = "Here are the tasks in your list:\n";
        for (int i = 0; i < length; i++) {
            Task task = this.taskList.get(i);
            String output = String.format("%d.%s", i + 1, task.describe());
            s += output + "\n";
        }
        return s;
    }

    public String find(String keyword) {
        String s = "Here are the matching tasks in your list:\n";
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
        String s = "Nice! I've marked this task as done:\n" + task.describe();
        return s;
    }

    public String unmark(int number) {
        Task task = taskList.get(number - 1);
        task.markUnDone();
        String s = "OK, I've marked this task as not done yet:\n" + task.describe();
        return s;
    }

    public int getNumOfTask() {
        return this.taskList.size();
    }

    public Boolean process(String input) throws ZzBotException {
        Parser parser = new Parser();
        String command = parser.parseCommand(input);

        switch (command) {
            case "bye": {
                this.ui.output(this.bye());
                break;
            }
            case "list": {
                this.ui.output(this.list());
                break;
            }
            case "unmark": {
                int number = parser.parseNumberArgument(input);
                this.ui.output(this.unmark(number));
                break;
            }
            case "mark": {
                int number = parser.parseNumberArgument(input);
                this.ui.output(this.mark(number));
                break;
            }
            case "todo": {
                String name = parser.parseTodoName(input);
                Task task = new ToDos(name);
                this.ui.output(this.add(task));
                break;
            }
            case "deadline": {
                String[] args = parser.parseDeadline(input);
                String name = args[0];
                String deadline = args[1].trim();
                LocalDate date = LocalDate.parse(deadline);
                Task task = new Deadlines(name, date);
                this.ui.output(this.add(task));
                break;
            }
            case "event": {
                String[] args = parser.parseEvent(input);
                String name = args[0].trim();
                String start = args[1].trim();
                String end = args[2].trim();
                Task task = new Events(name, start, end);
                this.ui.output(this.add(task));
                break;
            }
            case "delete": {
                int index = parser.parseNumberArgument(input);
                this.ui.output(this.delete(index));
                break;
            }
            case "find": {
                String keyword = parser.parseKeyword(input);
                this.ui.output(this.find(keyword));
                break;
            }
            default: {
                throw new ZzBotInvalidCommandException(command);
            }
        }

        return !command.equals("bye");
    }


    public void run() {
        this.ui.output(this.greet());
        while (true) {
            String input = ui.input();
            try {
                if (!this.process(input)) {
                    break;
                }
                this.storage.record(this.taskList);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

    }

    public static void main(String[] args) {
        new ZzBot("./data/zzBot.txt").run();
    }
}
