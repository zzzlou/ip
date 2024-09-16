package zzbot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Deadlines} class represents a task with a specific deadline.
 * It extends the {@code Task} class and adds a {@code LocalDate} attribute to
 * store the deadline date. This class provides methods to describe the deadline task
 * and to format the task information for file writing.
 */
public class Deadlines extends Task {

    private LocalDate deadline;
    public static final String TASK_TYPE = "D";

    /**
     * Constructs a new {@code Deadlines} task with the specified name and deadline date.
     * The task is initialized as not done.
     *
     * @param name     The name of the deadline task.
     * @param deadline The {@code LocalDate} object representing the deadline.
     */
    public Deadlines(String name, LocalDate deadline) {
        super(name, "D");
        this.deadline = deadline;
    }

    /**
     * Constructs a new {@code Deadlines} task with the specified name, completion status, and deadline date.
     *
     * @param name     The name of the deadline task.
     * @param isDone   The completion status of the task (true if done, false otherwise).
     * @param deadline The {@code LocalDate} object representing the deadline.
     */
    public Deadlines(String name, boolean isDone, LocalDate deadline) {
        super(name, isDone, TASK_TYPE);
        this.deadline = deadline;
    }

    /**
     * Provides a string representation of the deadline task, including its type, status, name, and deadline.
     * The deadline date is formatted as "MMM dd yyyy".
     *
     * @return A string describing the deadline task in the format: "[D][status] name (by: deadline)".
     */
    @Override
    public String describe() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return String.format("[%s][%s] %s (by: %s)",
                this.type, this.getStatusIcon(), this.getName(), this.deadline.format(outputFormatter));
    }

    /**
     * Formats the deadline task information for writing to a file.
     * The output is formatted as: "type|isDone|name|deadline\n".
     * The deadline date is represented in ISO-8601 format (YYYY-MM-DD).
     *
     * @return A string formatted for file writing, representing the deadline task.
     */
    @Override
    public String writeFile() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return this.type + "|" + (this.isDone ? "1" : "0") + "|" + this.name + "|" + this.deadline.toString() + "\n";
    }
}
