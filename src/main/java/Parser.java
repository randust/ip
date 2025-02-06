import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final String REGEX_EXPRESSION = "^(?<action>mark|unmark|list|todo|deadline|event)(\\s+(?<entity>.+))?";
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
                PrintFormat.println("Nyaa~ I don’t understand that action, nya! (；ΦωΦ) Please try again, meow!");
            }
        } else {
            PrintFormat.println("Meow? I didn’t catch that... Can you say it again, nya? (・・？)");
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
                PrintFormat.println("Here are all your tasks, nya~! (≧◡≦) ♡");
                for (int i = 0; i < Task.taskCount; i++) {
                    PrintFormat.println((i + 1) + ". " + NekoBot.tasks[i]);
                }
            }
        },
        TODO {
            @Override
            void execute(String entity) {
                NekoBot.tasks[Task.taskCount] = ToDo.createToDo(entity);
            }
        },
        DEADLINE {
            @Override
            void execute(String entity) {
                Pattern subPattern = Pattern.compile("(?<description>.+)\\s+/by\\s+(?<time>.+)");
                Matcher subMatcher = subPattern.matcher(entity);
                if (subMatcher.find()) {
                    NekoBot.tasks[Task.taskCount] = Deadline.createDeadline(
                            subMatcher.group("description"),
                            subMatcher.group("time")
                    );
                } else {
                    PrintFormat.println("Myaa~! That deadline format looks wrong... Try again? (╥﹏╥)");
                }
            }
        },
        EVENT {
            @Override
            void execute(String entity) {
                Pattern subPattern = Pattern.compile("(?<description>.+)\\s+/from\\s+(?<startTime>.+)\\s+/to\\s+(?<endTime>.+)");
                Matcher subMatcher = subPattern.matcher(entity);
                if (subMatcher.find()) {
                    NekoBot.tasks[Task.taskCount] = Event.createEvent(
                            subMatcher.group("description"),
                            subMatcher.group("startTime"),
                            subMatcher.group("endTime")
                    );
                } else {
                    PrintFormat.println("Myaa~! I didn’t understand the event details! (╥_╥) Please use: [event_name] /from [start] /to [end]!");
                }
            }
        };

        abstract void execute(String entity);

        private static void markTask(String entity, boolean isDone) {
            try {
                int taskNumber = Integer.parseInt(entity);
                if (taskNumber <= 0 || taskNumber > Task.taskCount) {
                    PrintFormat.println("Meow?! That task number doesn't exist! (ΦωΦ)");
                } else {
                    Task task = NekoBot.tasks[taskNumber - 1];
                    task.markStatus(isDone);
                }
            } catch (NumberFormatException e) {
                PrintFormat.println("Please give me a valid task number, nya! (≧ヘ≦)");
            }
        }
    }
}
