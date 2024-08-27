import java.util.Scanner;

class Ui {

    private Scanner sc;

    Ui() {
        this.sc = new Scanner(System.in);
    }

    public String input() {
        String input = this.sc.nextLine();
        return input;
    }

    public void showLoadingError() {
        System.out.println("Failed to load file");
    }

    public void output(String s) {
        System.out.println(s);
    }
    
}