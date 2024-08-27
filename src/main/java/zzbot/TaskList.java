package zzbot;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    
    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    TaskList() {
        this(new ArrayList<>());
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void delete(int index) {
        this.tasks.remove(index);
    }

    public Task get(int n) {
        return this.tasks.get(n);
    }

    public int size() {
        return this.tasks.size();
    }
}
