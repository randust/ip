package chatbot;

import chatbot.misc.NekoException;
import chatbot.misc.PrintFormat;
import chatbot.task.ActionType;
import chatbot.task.Deadline;
import chatbot.task.ToDo;
import chatbot.task.Event;
import chatbot.task.Task;

import chatbot.task.TaskManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class NekoBot {
    private static final List<String> ACTIONS = Arrays.asList("mark", "unmark", "todo", "deadline", "event", "list", "delete");
    private static final String FILEPATH = "neko.txt";

    public static void main(String[] args) {
        PrintFormat.printLine();
        PrintFormat.printLogo();
        PrintFormat.println("Loading Data...");
        loadData();
        PrintFormat.println("Nyaa~! I'm NekoBot! (≧▽≦)");
        PrintFormat.println("What can I do for you, nya~?");
        PrintFormat.printLine();

        Scanner reader = new Scanner(System.in);
        String userInput = reader.nextLine().trim(); // get user input

        while (!userInput.equals("bye")) {
            PrintFormat.printLine();
            parseUserInput(userInput);
            saveData();
            PrintFormat.printLine();
            PrintFormat.println("Meow~ Anything else, nya? (＾• ω •＾)");
            userInput = reader.nextLine().trim();
        }

        PrintFormat.println("Nyaa~ Bye bye! Hope to see you again soon!");
        PrintFormat.printLine();
    }

    private static void parseUserInput(String userInput) {
        String[] tokens = userInput.split("\\s+", 2);
        String actionStr = tokens[0];
        if (actionStr.isEmpty()) {
            PrintFormat.println("Meow? I didn’t catch that... Can you say it again, nya? (・・？)");
        } else if (!ACTIONS.contains(actionStr)) {
            PrintFormat.println("Nyaa~ I don’t understand that action, nya! Please try again, meow!");
        } else if (!actionStr.equals("list") && (tokens.length < 2 || tokens[1].isEmpty())) {
            PrintFormat.println("Meow~! Arguments cannot be empty, nya! (＾• ω •＾)");
        } else {
            String arguments = actionStr.equals("list") ? null : tokens[1];
            ActionType action = ActionType.valueOf(actionStr.toUpperCase());
            action.execute(arguments);
        }
    }

    private static void parseFileInput(String fileInput) throws NekoException {
        String[] elements = fileInput.split("\\s+\\|\\s+");
        if (elements[0].isEmpty() || elements[0].isBlank()) {
            throw new NekoException("is empty nya!");
        } else if (elements[0].equals("T")){
            ToDo todo = ToDo.createToDo(elements[2]);
            todo.markStatus(elements[1].equals("1"));
            TaskManager.addTask(todo);
        } else if (elements[0].equals("D")) {
            Deadline deadline = Deadline.createDeadline(elements[2], elements[3]);
            deadline.markStatus(elements[1].equals("1"));
            TaskManager.addTask(deadline);
        } else if (elements[0].equals("E")) {
            Event event = Event.createEvent(elements[2], elements[3], elements[4]);
            event.markStatus(elements[1].equals("1"));
            TaskManager.addTask(event);
        }
    }

    private static void saveData() {
        try (PrintWriter out = new PrintWriter(FILEPATH)) {
            for (int i = 0; i < TaskManager.getLength(); i++) {
                Task task = TaskManager.getTask(i);
                boolean isDone = task.getStatus().equals("[X]");
                String line = "";
                if (task instanceof ToDo) {
                    line = "T | " + (isDone?1:0) + " | " + task.getDescription();
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    line = "D | " + (isDone?1:0) + " | " + task.getDescription() + " | " + deadline.getTime();
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    line = "E | " + (isDone?1:0) + " | " + task.getDescription() + " | " + event.getStartTime() + " | " + event.getEndTime();
                }
                out.println(line);
            }
        } catch (IOException e) {

        }
    }

    private static void loadData() {
        File f = new File(FILEPATH);
        if (f.exists() && !f.isDirectory()) {
            try (BufferedReader br = new BufferedReader(new FileReader(FILEPATH))) {
                String line;
                int lineNum = 0;
                while ((line = br.readLine()) != null) {
                    lineNum++;
                    try {
                        parseFileInput(line);
                    } catch (IndexOutOfBoundsException e) {
                        PrintFormat.println("Format of line " + lineNum + " is incorrect nya! :/");
                    } catch (NekoException e) {
                        PrintFormat.println("Line " + lineNum + " " + e.getMessage());
                    }
                }
            } catch (IOException e) {

            }
        } else {
            try {
                File file = new File(FILEPATH);
                file.createNewFile();
            } catch (IOException e) {
            }
        }
    }
}
