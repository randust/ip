package chatbot.misc;

public class  UI{
    public static final String INDENT = "    ";
    public static final String SEPARATE_LINE = "____________________________________________________________";
    public static final String LOAD_MSG = "Loading Data...";
    public static final String WELCOME_MSG1 = "Nyaa~! I'm NekoBot!";
    public static final String WELCOME_MSG2 = "What can I do for you, nya~?";
    public static final String END_MSG = "Nyaa~ Bye bye! Hope to see you again soon!";
    public static final String CONTINUE_MSG = "Meow~ Anything else, nya?";
    public static final String END_INPUT = "bye";
    public static final String DISPLAY_TASK_MSG = "Here are all your tasks, nya~!";
    public static final String WRONG_DEADLINE_FORMAT_MSG = "Myaa~! That deadline format looks wrong... Try again?";
    public static final String WRONG_EVENT_DETAILS_MSG = "Myaa~! I didn’t understand the event details!";
    public static final String WRONG_TASK_NO_MSG = "Meow?! That task number doesn't exist!";
    public static final String REMOVE_TASK_MSG = "Okayy! I've removed this task:";
    public static final String PROVIDE_VALID_NO_MSG = "Please provide a valid task number, nya!";
    public static final String ADD_TASK_MSG = "Meow~! Got it! I've added this task for you!";
    public static final String DISPLAY_MATCHING_TASK_MSG = "Here are all your matching tasks, nya~!";

    public static void println(Object obj) {
        System.out.println(INDENT + obj);
    }

    public static void printf(Object obj) {
        System.out.printf(INDENT + obj);
    }

    public static void printLine() {
        System.out.println(INDENT + SEPARATE_LINE);
    }

    public static void printLogo() {
        System.out.println("\n" + INDENT +
                ".__   __.  _______  __  ___   ______   \n" + INDENT +
                "|  \\ |  | |   ____||  |/  /  /  __  \\  \n" + INDENT +
                "|   \\|  | |  |__   |  '  /  |  |  |  | \n" + INDENT +
                "|  . `  | |   __|  |    <   |  |  |  | \n" + INDENT +
                "|  |\\   | |  |____ |  .  \\  |  `--'  | \n" + INDENT +
                "|__| \\__| |_______||__|\\__\\  \\______/  \n" + INDENT +
                "                                       \n");
    }
}
