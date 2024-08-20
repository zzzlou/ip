import java.util.ArrayList;
import java.util.Scanner;

public class zzBot {

    private String name;
    private String line;
    private ArrayList<Task> taskList;

    public zzBot() {
        this.name = "zzBot";
        this.line = "____________________________________________________________";
        this.taskList = new ArrayList<>();
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

    public void add(String string) {
        this.taskList.add(new Task(string));
        System.out.println(String.format("added: %s", string));
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        zzBot bot = new zzBot();
        bot.greet();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                bot.bye();
                break;
            } else if (input.equals("list")) {
                bot.list();
            } else if (input.contains("unmark")) {
                int number = Integer.parseInt(input.substring(input.length() - 1));
                bot.unmark(number);
            } else if (input.contains("mark")) {
                int number = Integer.parseInt(input.substring(input.length() - 1));
                bot.mark(number);
            } else {
                bot.add(input);
            }
        }
    }
}


