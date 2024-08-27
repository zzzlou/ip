import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    private LocalDate deadline;

    Deadlines(String name, LocalDate deadline) {
        super(name, "D");
        this.deadline = deadline;
    }

    Deadlines(String name, boolean isDone, LocalDate deadline) {
        super(name, isDone, "D");
        this.deadline = deadline;
    }

    @Override
    public String describe() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String description = String.format("[%s][%s] %s (by: %s)",
                this.type, this.getStatusIcon(), this.getName(), this.deadline.format(outputFormatter));
        return description;
    }

    @Override
    public String writeFile() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return this.type + "|" + (this.isDone ? "1":"0") + "|" + this.name + "|" + this.deadline.toString() + "\n";
    }

    
}
