package zzbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the reading and writing of task data to and from a file.
 * This class is responsible for saving tasks to a specified file and loading tasks
 * from that file, ensuring data persistence across sessions.
 */
public class Storage {

    private static final String DEADLINE_TASK_TYPE = "D";
    private static final String EVENT_TASK_TYPE = "E";
    private static final String COMPLETED = "1";
    private String path;



    public Storage(String path) {
        this.path = path;
    }

    /**
     * Writes the current list of tasks to the file.
     *
     * @param tasks the list of tasks to be recorded to the file
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void record(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.path);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String text = task.writeFile();
            fw.write(text);
        }
        fw.close();
    }

    /**
     * Loads tasks from the file. If the file does not exist, it is created.
     *
     * @return an {@code ArrayList} of tasks loaded from the file
     */
    public ArrayList<Task> load() {
        File f = new File(this.path);
        ArrayList<Task> arr = new ArrayList<>();
        File dir = f.getParentFile();

        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] ss = line.split("\\|");
                String type = ss[0];
                boolean isDone = ss[1].equals(COMPLETED);
                String name = ss[2];

                if (type.equals(DEADLINE_TASK_TYPE)) {
                    String deadline = ss[3];
                    LocalDate date = LocalDate.parse(deadline);
                    Task task = new Deadlines(name, isDone, date);
                    arr.add(task);
                } else if (type.equals(EVENT_TASK_TYPE)) {
                    String start = ss[3];
                    String end = ss[4];
                    Task task = new Events(name, isDone, start, end);
                    arr.add(task);
                } else {
                    Task task = new ToDos(name, isDone);
                    arr.add(task);
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            try {
                f.createNewFile();
            } catch (IOException ioException) {
                System.out.println("An error occurred while creating the file: " + ioException.getMessage());
            }
        }
        return arr;
    }
}
