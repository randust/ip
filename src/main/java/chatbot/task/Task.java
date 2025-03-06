/**
 * Represents an abstract task in the chatbot's task management system.
 * This serves as the base class for different types of tasks.
 */
package chatbot.task;

import chatbot.misc.UI;

/**
 * Abstract class representing a general task with a description and completion status.
 */
public abstract class Task {

    /**
     * Description of the task.
     */
    protected String description;

    /**
     * Status of the task: true if completed, false otherwise.
     */
    protected boolean isDone;

    /**
     * Constructs a new task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    /**
     * Prints a message indicating that a task has been created and displays the current task count.
     *
     * @param task The newly created task.
     */
    protected static void printCreateTask(Task task) {
        UI.println(UI.ADD_TASK_MSG);
        UI.println(task);
        UI.println("Now you have " + TaskManager.getLength() + " tasks in your list, nya~!");
    }

    /**
     * Returns the status of the task as a formatted string.
     *
     * @return "[X]" if the task is done, "[ ]" otherwise.
     */
    public String getStatus() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Updates the completion status of the task.
     *
     * @param isDone True to mark the task as done, false to unmark.
     */
    public void markStatus(boolean isDone) {
        this.isDone = isDone; // mark or unmark task
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The formatted task string with status and description.
     */
    @Override
    public String toString() {
        return getStatus() + " " + getDescription();
    }

    /**
     * Returns a formatted string representation of the task for saving.
     *
     * @return The save format of the task.
     */
    public abstract String saveFormat();
}