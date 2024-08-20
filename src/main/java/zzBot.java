import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public void add(Task task) {
        this.taskList.add(task);
        String message = String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list",
                task.describe(),this.getNumOfTask());
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        zzBot bot = new zzBot();
        bot.greet();
        while (true) {
            String input = scanner.nextLine();
            String command = input.split(" ")[0];
            if (command.equals("bye")) {
                bot.bye();
                break;
            } else if (command.equals("list")) {
                bot.list();
            } else if (command.equals("unmark")) {
                String[] s = input.split(" ");
                int number = Integer.parseInt(s[s.length - 1]);
                bot.unmark(number);
            } else if (input.contains("mark")) {
                String[] s = input.split(" ");
                int number = Integer.parseInt(s[s.length - 1]);
                bot.mark(number);
            } else if (command.equals("todo")){
                System.out.println("detected");
                String name = input.substring(command.length() + 1);
                Task task = new ToDos(name);
                bot.add(task);
            } else if (command.equals("deadline")) {

                String[] s = input.substring(command.length() + 1).split(" /by ");

                String name = s[0];
                String deadline = s[1];
                Task task = new Deadlines(name,deadline);
                bot.add(task);

            } else if (command.equals("event")) {
                String[] s = input.split(" /from | /to ");
                String name = s[0].trim();
                String start = s[1].trim();
                String end = s[2].trim();
                Task task = new Events(name,start,end);
                bot.add(task);
            }
        }
    }
}


