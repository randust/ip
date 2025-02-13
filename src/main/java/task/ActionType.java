package task;
import misc.NekoException;
import misc.PrintFormat;
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
            PrintFormat.println("Here are all your tasks, nya~! (≧◡≦) ♡");
            for (int i = 0; i < TaskManager.getLength(); i++) {
                PrintFormat.println((i + 1) + ". " + TaskManager.getTask(i));
            }
        }
    }, TODO {
        @Override
        public void execute(String arguments) {
            ToDo todo = ToDo.createToDo(arguments);
            TaskManager.addTask(todo);
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
            } else {
                PrintFormat.println("Myaa~! I didn’t understand the event details!");
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
            throw new NekoException("Please provide a valid task number, nya! (≧ヘ≦)");
        }
    }

    abstract public void execute(String arguments);
}
