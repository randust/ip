package task;

import misc.NekoException;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public static ToDo createToDo(String description) {
        ToDo todo = new ToDo(description);
        printCreateTask(todo);
        return todo;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
