package chatbot.task;
import chatbot.misc.NekoException;
import chatbot.misc.UI;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;

/**
 * Enum representing different action commands that can be executed by the chatbot.
 * Each action type implements its own execution logic.
 */
public enum ActionType {
    /**
     * Marks a task as done.
     */
    MARK {
        @Override
        public void execute(String arguments) {
            try {
                markTask(arguments, true);
            } catch (NekoException e) {
                UI.println(e.getMessage());
            }
        }
    },

    /**
     * Unmarks a task (marks it as not done).
     */
    UNMARK {
        @Override
        public void execute(String arguments) {
            try {
                markTask(arguments, false);
            } catch (NekoException e) {
                UI.println(e.getMessage());
            }
        }
    },

    /**
     * Lists all tasks currently stored in TaskManager.
     */
    LIST {
        @Override
        public void execute(String arguments) {
            UI.println(UI.DISPLAY_TASK_MSG);
            for (int i = 0; i < TaskManager.getLength(); i++) {
                UI.println((i + 1) + ". " + TaskManager.getTask(i));
            }
        }
    },

    /**
     * Adds a new ToDo task to the TaskManager.
     */
    TODO {
        @Override
        public void execute(String arguments) {
            ToDo todo = ToDo.createToDo(arguments);
            TaskManager.addTask(todo);
            Task.printCreateTask(todo);
        }
    },

    /**
     * Adds a new Deadline task with a specified due date/time.
     */
    DEADLINE {
        @Override
        public void execute(String arguments) {
            final String DEADLINE_REGEX = "(?<description>.+)\\s+/by\\s+(?<time>.+)";
            Pattern subPattern = Pattern.compile(DEADLINE_REGEX);
            Matcher subMatcher = subPattern.matcher(arguments);
            if (subMatcher.find()) {
                try {
                    String description = subMatcher.group("description").trim();
                    String timeStr = subMatcher.group("time").trim();
                    LocalDate time = LocalDate.parse(timeStr);
                    Deadline deadline = Deadline.createDeadline(description, time);
                    TaskManager.addTask(deadline);
                    Task.printCreateTask(deadline);
                } catch (DateTimeParseException e) {
                    UI.println(UI.WRONG_TIME_FORMAT_MSG);
                }
            } else {
                UI.println(UI.WRONG_DEADLINE_FORMAT_MSG);
            }
        }
    },

    /**
     * Adds a new Event task with a specified start and end time.
     */
    EVENT {
        @Override
        public void execute(String arguments) {
            final String EVENT_REGEX = "(?<description>.+)\\s+/from\\s+(?<startTime>.+)\\s+/to\\s+(?<endTime>.+)";
            Pattern subPattern = Pattern.compile(EVENT_REGEX);
            Matcher subMatcher = subPattern.matcher(arguments);
            if (subMatcher.find()) {
                String description = subMatcher.group("description").trim();
                String startTime = subMatcher.group("startTime").trim();
                String endTime = subMatcher.group("endTime").trim();
                Event event = Event.createEvent(description, startTime, endTime);
                TaskManager.addTask(event);
                Task.printCreateTask(event);
            } else {
                UI.println(UI.WRONG_EVENT_DETAILS_MSG);
            }
        }
    },

    /**
     * Deletes a task from TaskManager based on the given task number.
     */
    DELETE {
        @Override
        public void execute(String arguments) {
            try {
                int taskNumber = Integer.parseInt(arguments.trim());
                if (taskNumber <= 0 || taskNumber > TaskManager.getLength()) {
                    UI.println(UI.WRONG_TASK_NO_MSG);
                    return;
                }
                Task removedTask = TaskManager.popTask(taskNumber - 1);
                UI.println(UI.REMOVE_TASK_MSG);
                UI.println(removedTask);
                int remainingTaskNumber = TaskManager.getLength();
                UI.println("Now you have " + remainingTaskNumber + (remainingTaskNumber > 1 ? " tasks in the list nya~!" : " task in the list nya~!"));
            } catch (NumberFormatException e) {
                UI.println(UI.PROVIDE_VALID_NO_MSG);
            }
        }
    },
    /**
     * Find a task from TaskManager based on the given description.
     */
    FIND {
        @Override
        public void execute(String arguments) {
            int j = 1;
            UI.println(UI.DISPLAY_MATCHING_TASK_MSG);
            for (int i = 0; i < TaskManager.getLength(); i++) {
                Task task = TaskManager.getTask(i);
                if (task.getDescription().contains(arguments)){
                    UI.println((j++) + ". " + task);
                }
            }
        }
    };

    /**
     * Marks or unmarks a task as done based on the given task number.
     *
     * @param arguments The task number to be marked/unmarked.
     * @param isDone    True to mark as done, false to unmark.
     * @throws NekoException If the task number is invalid.
     */
    private static void markTask(String arguments, boolean isDone) throws NekoException {
        try {
            int taskNumber = Integer.parseInt(arguments.trim());
            if (taskNumber <= 0 || taskNumber > TaskManager.getLength()) {
                throw new NekoException(UI.WRONG_TASK_NO_MSG);
            }
            Task task = TaskManager.getTask(taskNumber - 1);
            task.markStatus(isDone);
            UI.println("Task " + taskNumber + " has been " + (isDone ? "marked as done!" : "unmarked!") + " nya~!");
        } catch (NumberFormatException e) {
            throw new NekoException(UI.PROVIDE_VALID_NO_MSG);
        }
    }

    /**
     * Executes the corresponding action based on the provided arguments.
     *
     * @param arguments The arguments required for the action.
     */
    abstract public void execute(String arguments);
}
