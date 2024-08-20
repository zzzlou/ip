public class ToDos extends Task{

    ToDos(String name) {
        super(name,"T");
    }

    @Override
    public String describe() {
        String description = String.format("[%s][%s] %s",
                this.type,this.getStatusIcon(),this.getName());
        return description;
    }

}
