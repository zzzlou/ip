abstract class Task {
    protected String name;
    protected boolean isDone;
    protected String type;


    public Task(String name, String type) {
        this.name = name;
        this.isDone = false;
        this.type = type;
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
}
