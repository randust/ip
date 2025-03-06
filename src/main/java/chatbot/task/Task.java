package chatbot.task;

import chatbot.misc.UI;

public abstract class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    protected static void printCreateTask(Task task) {
        UI.println(UI.ADD_TASK_MSG);
        UI.println(task);
        UI.println("Now you have " + TaskManager.getLength() + " tasks in your list, nya~!");
    }

    public String getStatus() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markStatus(boolean isDone) {
        this.isDone = isDone; // mark or unmark task
    }

    public String getDescription() {
        return description;
    }
    @Override
    public String toString() {
        return getStatus() + " " + getDescription();
    }
    public abstract String saveFormat();
}
