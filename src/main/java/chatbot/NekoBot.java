package chatbot;

import misc.PrintFormat;
import task.ActionType;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

public class NekoBot {
    private static final List<String> ACTIONS = Arrays.asList("mark", "unmark", "todo", "deadline", "event", "list", "delete");
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
            userInput = reader.nextLine().trim();
        }

        PrintFormat.println("Nyaa~ Bye bye! Hope to see you again soon!");
        PrintFormat.printLine();
    }

    public static void parse(String userInput) {
        String[] tokens = userInput.split("\\s+", 2);
        String actionStr = tokens[0];
        if (actionStr.isEmpty()){
            PrintFormat.println("Meow? I didn’t catch that... Can you say it again, nya? (・・？)");
        } else if (!ACTIONS.contains(actionStr)) {
            PrintFormat.println("Nyaa~ I don’t understand that action, nya! Please try again, meow!");
        } else if (!actionStr.equals("list") && (tokens.length < 2 || tokens[1].isEmpty())) {
            PrintFormat.println("Meow~! Arguments cannot be empty, nya! (＾• ω •＾)");
        } else {
            String arguments = actionStr.equals("list")? null : tokens[1];
            ActionType action = ActionType.valueOf(actionStr.toUpperCase());
            action.execute(arguments);
        }
    }
}
