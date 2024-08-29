package zzbot;
abstract class Task {
    protected String name;
    protected boolean isDone;
    protected String type;

    public Task(String name, boolean isDone, String type) {
        this.name = name;
        this.isDone = isDone;
        this.type = type;
    }

    public Task(String name, String type) {
        this(name, false, type);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markUnDone() {
        this.isDone = false;
    }

    public String getName() {
        return name;
    }

    abstract String describe();

    abstract String writeFile();
}
