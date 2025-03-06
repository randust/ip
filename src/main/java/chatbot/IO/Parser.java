package chatbot.IO;

import chatbot.misc.UI;
import chatbot.task.ActionType;

import java.util.Arrays;
import java.util.List;

public class Parser {
    private static final List<String> ACTIONS = Arrays.asList("mark", "unmark", "todo", "deadline", "event", "list", "delete");

    public static void parseUserInput(String userInput) {
        String[] tokens = userInput.split("\\s+", 2);
        String actionStr = tokens[0];
        if (actionStr.isEmpty()) {
            UI.println("Meow? I didn’t catch that... Can you say it again, nya?");
        } else if (!ACTIONS.contains(actionStr)) {
            UI.println("Nyaa~ I don’t understand that action, nya! Please try again, meow!");
        } else if (!actionStr.equals("list") && (tokens.length < 2 || tokens[1].isEmpty())) {
            UI.println("Meow~! Arguments cannot be empty, nya!");
        } else {
            String arguments = actionStr.equals("list") ? null : tokens[1];
            ActionType action = ActionType.valueOf(actionStr.toUpperCase());
            action.execute(arguments);
        }
    }
}
