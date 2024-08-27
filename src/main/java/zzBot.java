import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class zzBot {

    private String name;
    private String line;
    private ArrayList<Task> taskList;

    public zzBot() {
        this.name = "zzBot";
        this.line = "____________________________________________________________";
        this.taskList = new ArrayList<>();
    }

    public zzBot(String path) {
        this.name = "zzBot";
        this.line = "____________________________________________________________";
        try {
            this.taskList = genList(path);
        } catch (IOException e) {
            System.out.println("Failed to load from file");
            this.taskList = new ArrayList<>();
        }
    }

    private ArrayList<Task> genList(String path) throws FileNotFoundException {
        File f = new File(path);
        if (!f.exists()) {
            System.out.println("no");
        }
        Scanner s = new Scanner(f);
        ArrayList<Task> arr = new ArrayList<>();
        while (s.hasNext()) {
            
            String line = s.nextLine();
            String[] ss = line.split("\\|");
            String type = ss[0];
            boolean isDone = ss[1] == "1" ? true:false;
            String name = ss[2];
            if (type.equals("D")) {
                String deadline = ss[3];
                LocalDate date = LocalDate.parse(deadline);
                Task task = new Deadlines(name, isDone,date);
                arr.add(task);
            } else if (type.equals("E")) {
                String start = ss[3];
                String end = ss[4];
                Task task = new Events(name, isDone,start, end);
                arr.add(task);
            } else {
                Task task = new ToDos(name,isDone);
                arr.add(task);
            }
        }
        s.close();

        return arr;

    }

    public void greet() {

        String message = this.line + String.format("\nHello! I'm %s", this.name) +
                "\nWhat can I do for you?\n" + this.line;

        System.out.println(message);
    }

    public void bye() {
        System.out.println(this.line + "\nBye. Hope to see you again soon!\n" + this.line);
    }

    public void echo(String input) {
        System.out.println(input);
    }

    public void add(Task task) {
        this.taskList.add(task);
        String message = String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list",
                task.describe(), this.getNumOfTask());
        System.out.println(message);
    }

    public void delete(int index) {
        Task task = this.taskList.get(index - 1);
        this.taskList.remove(index - 1);
        String message = String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                task.describe(), this.getNumOfTask());
        System.out.println(message);
    }

    public void list() {
        int length = this.taskList.size();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < length; i++) {
            Task task = this.taskList.get(i);
            String output = String.format("%d.%s", i + 1, task.describe());
            System.out.println(output);
        }
    }

    public void mark(int number) {
        Task task = taskList.get(number - 1);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + task.describe());
    }

    public void unmark(int number) {
        Task task = taskList.get(number - 1);
        task.markUnDone();
        System.out.println("OK, I've marked this task as not done yet:\n" + task.describe());
    }

    public int getNumOfTask() {
        return this.taskList.size();
    }

    public Boolean process(String input) throws ZzBotException {
        String command = input.split(" ")[0];
        switch (command) {
            case "bye": {
                this.bye();
                break;
            }
            case "list": {
                this.list();
                break;
            }
            case "unmark": {
                String[] s = input.split(" ");
                int number = Integer.parseInt(s[s.length - 1]);
                this.unmark(number);
                break;
            }
            case "mark": {
                String[] s = input.split(" ");
                int number = Integer.parseInt(s[s.length - 1]);
                this.mark(number);
                break;
            }
            case "todo": {

                if (input.length() <= command.length()) {
                    throw new ZzBotMissingArgumentException();
                }
                String name = input.substring(command.length() + 1).trim();
                Task task = new ToDos(name);
                this.add(task);
                break;
            }
            case "deadline": {
                try {
                    String[] s = input.substring(command.length() + 1).split(" /by ");

                    String name = s[0];
                    String deadline = s[1];
                    LocalDate date = LocalDate.parse(deadline);
                    Task task = new Deadlines(name, date);
                    this.add(task);
                    break;
                } catch (Exception e) {
                    throw new ZzBotParseCommandException(input);
                }
            }
            case "event": {
                try {
                    String[] s = input.split(" /from | /to ");
                    String name = s[0].trim();
                    String start = s[1].trim();
                    String end = s[2].trim();
                    Task task = new Events(name, start, end);
                    this.add(task);
                    break;
                } catch (Exception e) {
                    throw new ZzBotParseCommandException(input);
                }
            }
            case "delete": {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    this.delete(index);
                } catch (Exception e) {
                    throw new ZzBotDeletionException();
                }
                break;
            }
            default: {
                throw new ZzBotInvalidCommandException(command);
            }
        }

        return !command.equals("bye");
    }

    public void record(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : this.taskList) {
            String text = task.writeFile();
            fw.write(text);
        }
        fw.close();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String recordPath = "./data/zzBot.txt";
        zzBot bot = new zzBot(recordPath);
        bot.greet();
        while (true) {
            String input = scanner.nextLine();
            try {
                if (!bot.process(input)) {
                    break;
                }
                bot.record(recordPath);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
}
