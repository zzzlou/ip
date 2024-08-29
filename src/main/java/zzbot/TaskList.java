package zzbot;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code TaskList} class manages a list of {@code Task} objects.
 * It provides methods to add, delete, and retrieve tasks, as well as get the size of the list.
 */
public class TaskList {

    private List<Task> tasks;

    /**
     * Constructs a {@code TaskList} with the specified list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty {@code TaskList}.
     */
    TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Adds a task to the list.
     *
     * @param task The {@code Task} to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the list at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void delete(int index) {
        if (index >= 0 && index < tasks.size()) {
            this.tasks.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
        }
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The {@code Task} at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task get(int index) {
        if (index >= 0 && index < tasks.size()) {
            return this.tasks.get(index);
        } else {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }
}
