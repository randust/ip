package task;
import misc.PrintFormat;
public class Task{

    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatus(){
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markStatus(boolean isDone){
        this.isDone = isDone; // mark or unmark task
        if (isDone) {
            PrintFormat.println("Nyaa~! Good job, hooman! I've marked this task as done!!");
            PrintFormat.println(this);
        } else {
            PrintFormat.println("Okay! I've marked this task as not done yet nya~!");
            PrintFormat.println(this);
        }
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString(){
        return getStatus() + " " + getDescription();
    }

    public static Task createTask(String description){
        Task task = new Task(description);
        printCreateTask(task);
        return task;
    }

    protected static void printCreateTask(Task task){
        PrintFormat.println("Meow~! Got it! I've added this task for you!");
        PrintFormat.println(task);
        PrintFormat.println("Now you have " + TaskManager.getLength() + " tasks in your list, nya~!");
    }
}
