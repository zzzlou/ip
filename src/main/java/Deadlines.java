public class Deadlines extends Task{

    private String deadline;

    Deadlines(String name, String deadline) {
        super(name,"D");
        this.deadline = deadline;
    }

    @Override
    public String describe() {
        String description = String.format("[%s][%s] %s (by: %s)",
                this.type,this.getStatusIcon(),this.getName(),this.deadline);
        return description;
    }
}
