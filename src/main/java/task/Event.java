package task;

public class Event extends Task {
    private final String startTime, endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Event createEvent(String description, String startTime, String endTime) {
        Event event = new Event(description, startTime, endTime);
        Task.printCreateTask(event);
        return event;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
