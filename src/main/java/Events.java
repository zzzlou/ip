public class Events extends Task {

    private String start;
    private String end;

    Events(String name, String start, String end) {
        super(name, "E");
        this.start = start;
        this.end = end;
    }

    Events(String name, boolean isDone, String start, String end) {
        super(name, isDone, "E");
        this.start = start;
        this.end = end;
    }

    @Override
    public String describe() {
        String description = String.format("[%s][%s] %s (from: %s to: %s)",
                this.type, this.getStatusIcon(), this.getName(),
                this.start, this.end);
        return description;
    }

    @Override
    public String writeFile() {
        return this.type + "|" + (this.isDone ? "1":"0") + "|" + this.name + "|" + this.start + "|" + this.end + "\n";
    }

}
