package zzbot;

import java.util.Scanner;


/**
 * The {@code Ui} class handles user interaction through the console. It provides methods to read
 * user input, display output, and show error messages.
 */
public class Ui {

    private Scanner sc;

    /**
     * Constructs a {@code Ui} object with a {@code Scanner} for reading input from the console.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The input string entered by the user.
     */
    public String input() {
        return this.sc.nextLine();
    }

    /**
     * Displays an error message indicating that a file failed to load.
     */
    public void showLoadingError() {
        System.out.println("Failed to load file");
    }

    /**
     * Outputs the specified string to the console.
     *
     * @param s The string to be displayed.
     */
    public String output(String s) {
        return s;
    }
}
