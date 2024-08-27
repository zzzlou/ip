public class ToDos extends Task{

    ToDos(String name) {
        super(name,"T");
    }

    ToDos(String name, boolean isDone) {
        super(name,isDone, "T");
    }

    @Override
    public String describe() {
        String description = String.format("[%s][%s] %s",
                this.type,this.getStatusIcon(),this.getName());
        return description;
    }

    @Override
    public String writeFile() {
        return this.type + "|" + this.isDone + "|" + this.name + "\n";
    }

}
