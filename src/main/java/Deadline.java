public class Deadline extends Task{
    private String time;

    public static Deadline createDeadline(String description, String time){
        Deadline deadline = new Deadline(description, time);
        printCreateTask(deadline);
        return deadline;
    }

    public Deadline(String description, String time){
        super(description);
        this.time = time;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}
