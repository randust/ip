package chatbot.task;

public class Event extends Task {
    private final String startTime, endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Event createEvent(String description, String startTime, String endTime) {
        Event event = new Event(description, startTime, endTime);
        return event;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }

    @Override
    public String saveFormat(){
        return "E | " + (isDone?1:0) + " | " + description + " | " + startTime + " | " + endTime;
    }
}
