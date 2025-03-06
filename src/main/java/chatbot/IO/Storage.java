/**
 * Handles storage-related operations for saving and loading chatbot tasks.
 * This class provides functionality to read from and write to a text file.
 */
package chatbot.IO;

import chatbot.misc.NekoException;
import chatbot.misc.UI;
import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.TaskManager;
import chatbot.task.ToDo;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeParseException;

/**
 * Manages file storage for chatbot tasks, allowing data persistence across sessions.
 */
public class Storage {
    /**
     * File path where tasks are stored.
     */
    private static final String FILEPATH = "neko.txt";

    /**
     * Parses a line of input from the storage file and creates the corresponding task.
     *
     * @param fileInput A formatted string representing a task.
     * @throws NekoException If the input is malformed or missing required fields.
     */
    public static void parseFileInput(String fileInput) throws NekoException {
        String[] elements = fileInput.split("\\s+\\|\\s+");
        if (elements[0].isEmpty() || elements[0].isBlank()) {
            throw new NekoException("is empty nya!");
        } else {
            switch (elements[0]) {
            case "T" -> {
                ToDo todo = ToDo.createToDo(elements[2]);
                todo.markStatus(elements[1].equals("1"));
                TaskManager.addTask(todo);
            }
            case "D" -> {
                try {
                    LocalDate time = LocalDate.parse(elements[3]);
                    Deadline deadline = Deadline.createDeadline(elements[2], time);
                    deadline.markStatus(elements[1].equals("1"));
                    TaskManager.addTask(deadline);
                } catch (DateTimeParseException e) {
                    UI.println(UI.WRONG_TIME_FORMAT_MSG);
                }
            }
            case "E" -> {
                Event event = Event.createEvent(elements[2], elements[3], elements[4]);
                event.markStatus(elements[1].equals("1"));
                TaskManager.addTask(event);
            }
            }
        }
    }

    /**
     * Saves the current list of tasks to a file.
     */
    public static void saveData() {
        try (PrintWriter out = new PrintWriter(FILEPATH)) {
            for (int i = 0; i < TaskManager.getLength(); i++) {
                Task task = TaskManager.getTask(i);
                String line = task.saveFormat();
                out.println(line);
            }
        } catch (IOException e) {
            UI.println("There is some problem saving the data!");
        }
    }

    /**
     * Loads tasks from the storage file and adds them to the task manager.
     * If the file does not exist, it creates a new empty file.
     */
    public static void loadData() {
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
                        UI.println("Format of line " + lineNum + " is incorrect nya! :/");
                    } catch (NekoException e) {
                        UI.println("Line " + lineNum + " " + e.getMessage());
                    }
                }
            } catch (IOException e) {
                UI.println("File " + FILEPATH + " cannot be loaded!");
            }
        } else {
            try {
                File file = new File(FILEPATH);
                file.createNewFile();
            } catch (IOException e) {
                UI.println("File " + FILEPATH + " cannot be created!");
            }
        }
    }
}