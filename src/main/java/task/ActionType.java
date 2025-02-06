package task;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import misc.PrintFormat;
public enum ActionType {
    MARK {
        @Override
        public void execute(String entity) {
            markTask(entity, true);
        }
    },
    UNMARK {
        @Override
        public void execute(String entity) {
            markTask(entity, false);
        }
    },
    LIST {
        @Override
        public void execute(String entity) {
            PrintFormat.println("Here are all your tasks, nya~! (≧◡≦) ♡");
            for (int i = 0; i < TaskManager.getLength(); i++) {
                PrintFormat.println((i + 1) + ". " + TaskManager.getTask(i));
            }
        }
    },
    TODO {
        @Override
        public void execute(String entity) {
            TaskManager.addTask(ToDo.createToDo(entity));
        }
    },
    DEADLINE {
        @Override
        public void execute(String entity) {
            Pattern subPattern = Pattern.compile("(?<description>.+)\\s+/by\\s+(?<time>.+)");
            Matcher subMatcher = subPattern.matcher(entity);
            if (subMatcher.find()) {
                TaskManager.addTask(
                    Deadline.createDeadline(
                    subMatcher.group("description"),
                    subMatcher.group("time")
                    )
                );
            } else {
                PrintFormat.println("Myaa~! That deadline format looks wrong... Try again?");
            }
        }
    },
    EVENT {
        @Override
        public void execute(String entity) {
            Pattern subPattern = Pattern.compile("(?<description>.+)\\s+/from\\s+(?<startTime>.+)\\s+/to\\s+(?<endTime>.+)");
            Matcher subMatcher = subPattern.matcher(entity);
            if (subMatcher.find()) {
                TaskManager.addTask(
                    Event.createEvent(
                    subMatcher.group("description"),
                    subMatcher.group("startTime"),
                    subMatcher.group("endTime")
                    )
                );
            } else {
                PrintFormat.println("Myaa~! I didn’t understand the event details! Please use: [event_name] /from [start] /to [end]!");
            }
        }
    };

    abstract public void execute(String entity);

    private static void markTask(String entity, boolean isDone) {
        try {
            int taskNumber = Integer.parseInt(entity);
            if (taskNumber <= 0 || taskNumber > TaskManager.getLength()) {
                PrintFormat.println("Meow?! That task number doesn't exist!");
            } else {
                Task task = TaskManager.getTask(taskNumber - 1);
                task.markStatus(isDone);
            }
        } catch (NumberFormatException e) {
            PrintFormat.println("Please give me a valid task number, nya! (≧ヘ≦)");
        }
    }
}
