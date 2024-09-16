package zzbot;

/**
 * The {@code Task} class is an abstract class representing a general task.
 * It contains attributes such as the task name, whether the task is done, and the task type.
 * Subclasses are expected to implement methods to describe the task and to format the task for file writing.
 */
abstract class Task {
    protected String name;
    protected boolean isDone;
    protected String type;

    /**
     * Constructs a new {@code Task} with the specified name, completion status, and task type.
     *
     * @param name   The name of the task.
     * @param isDone The completion status of the task (true if done, false otherwise).
     * @param type   The type of the task (e.g., "T" for ToDo, "D" for Deadline, "E" for Event).
     */
    public Task(String name, boolean isDone, String type) {
        this.name = name;
        this.isDone = isDone;
        this.type = type;
    }

    /**
     * Constructs a new {@code Task} with the specified name and task type.
     * The task is initialized as not done.
     *
     * @param name The name of the task.
     * @param type The type of the task (e.g., "T" for ToDo, "D" for Deadline, "E" for Event).
     */
    public Task(String name, String type) {
        this(name, false, type);
    }

    /**
     * Returns the status icon of the task, where "X" represents a completed task and a blank space represents an incomplete task.
     *
     * @return The status icon, "X" if the task is done, otherwise a blank space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markUnDone() {
        this.isDone = false;
    }

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Provides a string description of the task. This method must be implemented by subclasses.
     *
     * @return A string describing the task.
     */
    abstract String describe();

    /**
     * Formats the task information for file writing. This method must be implemented by subclasses.
     *
     * @return A string formatted for file writing, representing the task.
     */
    abstract String writeFile();
}
