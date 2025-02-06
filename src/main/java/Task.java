public class Task{

    protected String description;
    protected boolean isDone;
    public static int taskCount = 0;

    public Task(String description){
        this.description = description;
        this.isDone = false;
        Task.taskCount++;
    }

    public String getStatus(){
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markStatus(boolean isDone){
        this.isDone = isDone; // mark or unmark task
        if (isDone) {
            PrintFormat.println("Nyaa~! Good job, hooman! I've marked this task as done!! (≧▽≦)ฅ");
            PrintFormat.println(this);
        } else {
            PrintFormat.println("Okay! I've marked this task as not done yet nya~! (＾• ω •＾)");
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
        PrintFormat.println("Meow~! Got it! I've added this task for you! (ฅ^•ﻌ•^ฅ)");
        PrintFormat.println(task);
        PrintFormat.println("Now you have " + Task.taskCount + " tasks in your list, nya~!");
    }
}
