package zzbot;

/**
 * The {@code ToDos} class represents a simple to-do task.
 * It extends the {@code Task} class and provides specific implementations
 * for describing the task and formatting it for file storage.
 */
public class ToDos extends Task {

    /**
     * Constructs a new {@code ToDos} task with the specified name.
     * The task is initialized as not done.
     *
     * @param name The name of the to-do task.
     */
    public ToDos(String name) {
        super(name, "T");
    }

    /**
     * Constructs a new {@code ToDos} task with the specified name and completion status.
     *
     * @param name   The name of the to-do task.
     * @param isDone The completion status of the task (true if done, false otherwise).
     */
    public ToDos(String name, boolean isDone) {
        super(name, isDone, "T");
    }

    /**
     * Provides a string representation of the to-do task, including its type, status, and name.
     *
     * @return A string describing the to-do task in the format: "[T][status] name".
     */
    @Override
    public String describe() {
        return String.format("[%s][%s] %s", this.type, this.getStatusIcon(), this.getName());
    }

    /**
     * Formats the to-do task information for writing to a file.
     * The output is formatted as: "T|isDone|name\n".
     *
     * @return A string formatted for file writing, representing the to-do task.
     */
    @Override
    public String writeFile() {
        return this.type + "|" + (this.isDone ? "1" : "0") + "|" + this.name + "\n";
    }
}
