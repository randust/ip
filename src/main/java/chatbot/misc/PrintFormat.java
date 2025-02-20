package chatbot.misc;

public class PrintFormat {
    public static final String INDENT = "    ";
    public static final String SEPARATE_LINE = "____________________________________________________________";


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
