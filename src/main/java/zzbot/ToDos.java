package zzbot;
public class ToDos extends Task{

    ToDos(String name) {
        super(name,"T");
    }

    ToDos(String name, boolean isDone) {
        super(name,isDone, "T");
    }

    @Override
    public String describe() {
        return String.format("[%s][%s] %s",
                this.type, this.getStatusIcon(), this.getName());
    }

    @Override
    public String writeFile() {
        return this.type + "|" + (this.isDone ? "1":"0") + "|" + this.name + "\n";
    }

}
