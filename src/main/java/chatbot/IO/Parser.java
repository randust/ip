package chatbot.IO;

import chatbot.misc.UI;
import chatbot.task.ActionType;

import java.util.Arrays;
import java.util.List;

public class Parser {
    private static final List<String> ACTIONS = Arrays.asList("mark", "unmark", "todo", "deadline", "event", "list", "delete", "find");

    public static void parseUserInput(String userInput) {
        String[] tokens = userInput.split("\\s+", 2);
        String actionStr = tokens[0];
        if (actionStr.isEmpty()) {
            UI.println(UI.WRONG_COMMAND_MSG);
        } else if (!ACTIONS.contains(actionStr)) {
            UI.println(UI.WRONG_ACTION_MSG);
        } else if (!actionStr.equals("list") && (tokens.length < 2 || tokens[1].isEmpty())) {
            UI.println(UI.ARG_EMPTY_MSG);
        } else {
            String arguments = actionStr.equals("list") ? null : tokens[1];
            ActionType action = ActionType.valueOf(actionStr.toUpperCase());
            action.execute(arguments);
        }
    }
}
