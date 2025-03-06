package chatbot.task;

public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    public static ToDo createToDo(String description) {
        ToDo todo = new ToDo(description);
        return todo;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveFormat(){
        return "T | " + (isDone?1:0) + " | " + description;
    }
}
