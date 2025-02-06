package task;
public class ToDo extends Task{
    public static ToDo createToDo(String description){
        ToDo todo = new ToDo(description);
        printCreateTask(todo);
        return todo;
    }

    public ToDo(String description){
        super(description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
