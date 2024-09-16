package zzbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String path;
    private static final String DEADLINE_TASK_TYPE = "D";
    private static final String EVENT_TASK_TYPE = "E";
    private static final String COMPLETED = "1";


    public Storage(String path) {
        this.path = path;
    }

    public void record(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.path);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String text = task.writeFile();
            fw.write(text);
        }
        fw.close();
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(this.path);
        Scanner s = new Scanner(f);
        ArrayList<Task> arr = new ArrayList<>();
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

        return arr;

    }

}
