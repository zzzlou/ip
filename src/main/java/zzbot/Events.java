package zzbot;

/**
 * The {@code Events} class represents a task that occurs over a specific time period.
 * It extends the {@code Task} class and adds attributes to store the start and end times of the event.
 * This class provides methods to describe the event task and to format the task information for file writing.
 */
public class Events extends Task {

    public static final String TASK_TYPE = "E";
    private String start;
    private String end;

    /**
     * Constructs a new {@code Events} task with the specified name, start time, and end time.
     * The task is initialized as not done.
     *
     * @param name  The name of the event task.
     * @param start The start time of the event.
     * @param end   The end time of the event.
     */
    public Events(String name, String start, String end) {
        super(name, "E");
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a new {@code Events} task with the specified name, completion status, start time, and end time.
     *
     * @param name   The name of the event task.
     * @param isDone The completion status of the task (true if done, false otherwise).
     * @param start  The start time of the event.
     * @param end    The end time of the event.
     */
    public Events(String name, boolean isDone, String start, String end) {
        super(name, isDone, TASK_TYPE);
        this.start = start;
        this.end = end;
    }

    /**
     * Provides a string representation of the event task, including its type, status, name, start time, and end time.
     *
     * @return A string describing the event task in the format: "[E][status] name (from: start to: end)".
     */
    @Override
    public String describe() {
        String description = String.format("[%s][%s] %s (from: %s to: %s)",
                this.type, this.getStatusIcon(), this.getName(),
                this.start, this.end);
        return description;
    }

    /**
     * Formats the event task information for writing to a file.
     * The output is formatted as: "type|isDone|name|start|end\n".
     *
     * @return A string formatted for file writing, representing the event task.
     */
    @Override
    public String writeFile() {
        return this.type + "|" + (this.isDone ? "1" : "0") + "|" + this.name + "|" + this.start + "|" + this.end + "\n";
    }
}
