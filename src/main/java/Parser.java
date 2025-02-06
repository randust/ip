import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Parser {
    private static final String REGEX_EXPRESSION = "(?<action>mark|unmark|list|todo|deadline|event)(\\s+(?<entity>.+))?";
    private static final Pattern PATTERN = Pattern.compile(REGEX_EXPRESSION);

    public static void addTask(String userInput) {
        Matcher matcher = PATTERN.matcher(userInput);

        if (matcher.find()) {
            String actionStr = matcher.group("action").toUpperCase();
            String entity = matcher.group("entity");

            try {
                ActionType action = ActionType.valueOf(actionStr);
                action.execute(entity);
            } catch (IllegalArgumentException e) {
                PrintFormat.println("Unknown action: " + actionStr);
            }
        } else {
            PrintFormat.println("No valid match found.");
        }
    }

    enum ActionType {
        MARK {
            @Override
            void execute(String entity) {
                markTask(entity, true);
            }
        },
        UNMARK {
            @Override
            void execute(String entity) {
                markTask(entity, false);
            }
        },
        LIST {
            @Override
            void execute(String entity) {
                PrintFormat.println("Here are the tasks in your list:");
                for (int i = 0; i < Task.taskCount; i++){
                    PrintFormat.println((i + 1) + "." + Lava.tasks[i]); //list out (print) tasks
                }
            }
        },
        TODO {
            @Override
            void execute(String entity) {
                Lava.tasks[Task.taskCount] = ToDo.createToDo(entity);
            }
        },
        DEADLINE {
            @Override
            void execute(String entity) {
                Pattern subPattern = Pattern.compile("(?<description>.+)\\s+/by\\s+(?<time>.+)");
                Matcher subMatcher = subPattern.matcher(entity);
                if (subMatcher.find()) {
                    Lava.tasks[Task.taskCount] = Deadline.createDeadline(
                            subMatcher.group("description"),
                            subMatcher.group("time")
                    );
                } else {
                    PrintFormat.println("Error: Input does not match the expected format.");
                }
            }
        },
        EVENT {
            @Override
            void execute(String entity) {
                Pattern subPattern = Pattern.compile("(?<description>.+)\\s+/from\\s+(?<startTime>.+)\\s+/to\\s+(?<endTime>.+)");
                Matcher subMatcher = subPattern.matcher(entity);
                if (subMatcher.find()) {
                    Lava.tasks[Task.taskCount] = Event.createEvent(
                            subMatcher.group("description"),
                            subMatcher.group("startTime"),
                            subMatcher.group("endTime")
                    );
                }else {
                    PrintFormat.println("Error: Input does not match the expected format.");
                }
            }
        };

        abstract void execute(String entity);


        private static void markTask(String entity, boolean isDone){
            int taskNumber = Integer.parseInt(entity);
            if (taskNumber <= 0 || taskNumber > Task.taskCount) {
                PrintFormat.println("Invalid task number!");
            }else {
                Task task = Lava.tasks[taskNumber - 1];
                task.markStatus(isDone);
            }
        }
    }

}
