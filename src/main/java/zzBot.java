public class zzBot {

    public String name;
    public String line;

    public zzBot() {
        this.name = "zzBot";
        this.line = "____________________________________________________________";
    }

    public void greet() {

        String message = this.line + String.format("\nHello! I'm %s",this.name) +
                "\nWhat can I do for you?\n" + this.line +
                "\nBye. Hope to see you again soon!\n" + this.line;

        System.out.println(message);
    }

    public static void main(String[] args) {
        zzBot bot = new zzBot();
        bot.greet();
    }
}
