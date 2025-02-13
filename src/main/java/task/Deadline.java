package task;

import misc.NekoException;

public class Deadline extends Task {
    private final String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    public static Deadline createDeadline(String description, String time) {
        Deadline deadline = new Deadline(description, time);
        Task.printCreateTask(deadline);
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}
