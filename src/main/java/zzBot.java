import java.util.ArrayList;
import java.util.Scanner;

public class zzBot {

    private String name;
    private String line;
    private ArrayList<String> botList;

    public zzBot() {
        this.name = "zzBot";
        this.line = "____________________________________________________________";
        this.botList = new ArrayList<>();
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
        botList.add(string);
        System.out.println(String.format("added: %s",string));
    }

    public void list() {
        int length = this.botList.size();
        for (int i = 0; i < length; i++) {
            String output = String.format("%d.  %s", i + 1, this.botList.get(i));
            System.out.println(output);
        }
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
            } else {
                bot.add(input);
            }
        }
    }
}
