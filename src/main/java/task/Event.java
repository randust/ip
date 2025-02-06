package task;
public class Event extends Task {
    String startTime, endTime;
    public static Event createEvent(String description, String startTime, String endTime){
        Event event = new Event(description, startTime, endTime);
        Task.printCreateTask(event);
        return event;
    }

    public Event(String description, String startTime, String endTime){
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
