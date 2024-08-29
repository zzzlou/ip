package zzbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code Storage} class handles reading from and writing to a file,
 * storing and loading {@code Task} objects for persistence.
 */
public class Storage {

    private String path;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     *
     * @param path The path of the file for storage.
     */
    Storage(String path) {
        this.path = path;
    }

    /**
     * Records the tasks from the given {@code TaskList} to the file.
     *
     * @param tasks The {@code TaskList} containing tasks to record.
     * @throws IOException If an I/O error occurs.
     */
    public void record(TaskList tasks) throws IOException {
        try (FileWriter fw = new FileWriter(this.path)) {
            for (Task task : tasks) {
                fw.write(task.writeFile());
            }
        }
    }

    /**
     * Loads tasks from the file into an {@code ArrayList} of {@code Task}.
     *
     * @return An {@code ArrayList} containing the loaded tasks.
     * @throws FileNotFoundException If the file is not found.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(this.path);

        if (!file.exists()) {
            System.out.println("File not found");
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String[] parts = scanner.nextLine().split("\\|");
                Task task = createTaskFromData(parts);
                tasks.add(task);
            }
        }

        return tasks;
    }

    /**
     * Creates a task based on the given data array.
     *
     * @param data The string array containing task data.
     * @return The created {@code Task} object.
     */
    private Task createTaskFromData(String[] data) {
        String type = data[0];
        boolean isDone = "1".equals(data[1]);
        String name = data[2];

        switch (type) {
            case "D":
                LocalDate deadline = LocalDate.parse(data[3]);
                return new Deadlines(name, isDone, deadline);
            case "E":
                String start = data[3];
                String end = data[4];
                return new Events(name, isDone, start, end);
            default:
                return new ToDos(name, isDone);
        }
    }
}
