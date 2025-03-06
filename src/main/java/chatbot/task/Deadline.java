package chatbot.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate time;

    public Deadline(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    public static Deadline createDeadline(String description, LocalDate time) {
        Deadline deadline = new Deadline(description, time);
        return deadline;
    }

    public String getTime() {
        return time.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String saveFormat(){
        return "D | " + (isDone?1:0) + " | " + description + " | " + time.toString();
    }
}
