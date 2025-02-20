package chatbot.task;
import chatbot.misc.NekoException;
import chatbot.misc.PrintFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ActionType {
    MARK {
        @Override
        public void execute(String arguments) {
            try {
                markTask(arguments, true);
            } catch (NekoException e) {
                PrintFormat.println(e.getMessage());
            }
        }
    }, UNMARK {
        @Override
        public void execute(String arguments) {
            try {
                markTask(arguments, false);
            } catch (NekoException e) {
                PrintFormat.println(e.getMessage());
            }
        }
    }, LIST {
        @Override
        public void execute(String arguments) {
            PrintFormat.println("Here are all your tasks, nya~!");
            for (int i = 0; i < TaskManager.getLength(); i++) {
                PrintFormat.println((i + 1) + ". " + TaskManager.getTask(i));
            }
        }
    }, TODO {
        @Override
        public void execute(String arguments) {
            ToDo todo = ToDo.createToDo(arguments);
            TaskManager.addTask(todo);
            Task.printCreateTask(todo);
        }
    }, DEADLINE {
        @Override
        public void execute(String arguments) {
            final String DEADLINE_REGEX = "(?<description>.+)\\s+/by\\s+(?<time>.+)";
            Pattern subPattern = Pattern.compile(DEADLINE_REGEX);
            Matcher subMatcher = subPattern.matcher(arguments);
            if (subMatcher.find()) {
                String description = subMatcher.group("description").trim();
                String time = subMatcher.group("time").trim();
                Deadline deadline = Deadline.createDeadline(description, time);
                TaskManager.addTask(deadline);
                Task.printCreateTask(deadline);
            } else {
                PrintFormat.println("Myaa~! That deadline format looks wrong... Try again?");
            }
        }
    }, EVENT {
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
                PrintFormat.println("Myaa~! I didn’t understand the event details!");
            }
        }
    }, DELETE {
        @Override
        public void execute(String arguments) {
            try {
                int taskNumber = Integer.parseInt(arguments.trim());
                if (taskNumber <= 0 || taskNumber > TaskManager.getLength()) {
                    PrintFormat.println("Meow?! That task number doesn't exist!");
                    return;
                }
                Task removedTask = TaskManager.popTask(taskNumber - 1);
                PrintFormat.println("Okayy! I've removed this task:");
                PrintFormat.println(removedTask);
                int remainingTaskNumber = TaskManager.getLength();
                PrintFormat.println("Now you have " + remainingTaskNumber + (remainingTaskNumber > 1 ? " tasks in the list nya~!" : " task in the list nya~!"));
            } catch (NumberFormatException e) {
                PrintFormat.println("Please provide a valid task number, nya!");
            }
        }
    };

    private static void markTask(String arguments, boolean isDone) throws NekoException {
        try {
            int taskNumber = Integer.parseInt(arguments.trim());
            if (taskNumber <= 0 || taskNumber > TaskManager.getLength()) {
                throw new NekoException("Meow?! That task number doesn't exist!");
            }
            Task task = TaskManager.getTask(taskNumber - 1);
            task.markStatus(isDone);
            PrintFormat.println("Task " + taskNumber + " has been " + (isDone ? "marked as done!" : "unmarked!") + " nya~!");
        } catch (NumberFormatException e) {
            throw new NekoException("Please provide a valid task number, nya!");
        }
    }

    abstract public void execute(String arguments);
}
