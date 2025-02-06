import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import misc.PrintFormat;
import task.ActionType;
public class NekoBot {
    private static final String REGEX_EXPRESSION = "^(?<action>mark|unmark|list|todo|deadline|event)(\\s+(?<entity>.+))?";
    private static final Pattern PATTERN = Pattern.compile(REGEX_EXPRESSION);

    public static void main(String[] args) {
        PrintFormat.printLine();
        PrintFormat.printLogo();
        PrintFormat.println("Nyaa~! I'm NekoBot! (≧▽≦)");
        PrintFormat.println("What can I do for you, nya~?");
        PrintFormat.printLine();

        Scanner reader = new Scanner(System.in);
        String userInput = reader.nextLine().trim(); // get user input

        while (!userInput.equals("bye")) {
            PrintFormat.printLine();
            parse(userInput);
            PrintFormat.printLine();
            PrintFormat.println("Meow~ Anything else, nya? (＾• ω •＾)");
            userInput = reader.nextLine();
        }

        PrintFormat.println("Nyaa~ Bye bye! Hope to see you again soon!");
        PrintFormat.printLine();
    }

    public static void parse(String userInput) {
        Matcher matcher = PATTERN.matcher(userInput);

        if (matcher.find()) {
            String actionStr = matcher.group("action").toUpperCase();
            String entity = matcher.group("entity");

            try {
                ActionType action = ActionType.valueOf(actionStr);
                action.execute(entity);
            } catch (IllegalArgumentException e) {
                PrintFormat.println("Nyaa~ I don’t understand that action, nya! Please try again, meow!");
            }
        } else {
            PrintFormat.println("Meow? I didn’t catch that... Can you say it again, nya? (・・？)");
        }
    }
}
