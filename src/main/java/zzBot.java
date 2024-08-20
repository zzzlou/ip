import java.util.Scanner;
public class zzBot {

    public String name;
    public String line;

    public zzBot() {
        this.name = "zzBot";
        this.line = "____________________________________________________________";
    }

    public void greet() {

        String message = this.line + String.format("\nHello! I'm %s",this.name) +
                "\nWhat can I do for you?\n" + this.line;

        System.out.println(message);
    }

    public void bye() {
        System.out.println(this.line + "\nBye. Hope to see you again soon!\n" + this.line);
    }

    public void echo(String input) {
        System.out.println(input);
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
            } else {
                bot.echo(input);
            }
        }
    }
}
