import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Lava {
    public static class Task{
        protected String name;
        protected boolean isDone;

        public Task(String name){
            this.name = name;
            this.isDone = false;
        }

        public String getStatus(){
            return (isDone ? "[X]" : "[ ]"); // mark done task with X
        }

        public void markStatus(boolean isDone){
            this.isDone = isDone; // mark or unmark task
        }

        public String getName() {
            return name;
        }

        public String toString(){
            return getStatus() + " " + getName();
        }
    }
    public static void main(String[] args) {
        String indent = "    ";
        System.out.println(indent + "____________________________________________________________");
        System.out.println(indent + "Hello I'm Lava.");
        System.out.println(indent + "What can I do for you?");
        System.out.println(indent + "____________________________________________________________");

        Scanner reader = new Scanner(System.in);
        String userInput = reader.nextLine().trim(); // get user userInput
        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (!userInput.equals("bye")){
            System.out.println(indent + "____________________________________________________________");
            // Regex for "mark (number)" or "unmark (number)"
            Pattern markPattern = Pattern.compile("^(mark|unmark)\\s+(\\d+)$");
            Matcher matcher = markPattern.matcher(userInput);
            if (matcher.matches()) {
                String marking = matcher.group(1); // "mark" or "unmark"
                int taskNumber = Integer.parseInt(matcher.group(2));

                if (taskNumber <= 0 || taskNumber > taskCount) {
                    System.out.println(indent + "Invalid task number!");
                }else {
                    Task task = tasks[taskNumber - 1];
                    if (marking.equals("mark")) {
                        task.markStatus(true);
                        System.out.println(indent + "Nice! I've marked this task as done:");
                        System.out.println(indent + task);
                    } else {
                        task.markStatus(false);
                        System.out.println(indent + "OK, I've marked this task as not done yet:");
                        System.out.println(indent + task);
                    }
                }
            }else if (userInput.equals("list")){
                for (int i = 0; i < taskCount; i++){
                    System.out.println(indent + (i + 1) + "." + tasks[i]); //list out (print) tasks
                }
            } else {
                tasks[taskCount++] = new Task(userInput);
                System.out.println(indent + "added: " + userInput); //add tasks
            }
            System.out.println(indent + "____________________________________________________________");
            userInput = reader.nextLine();
        }
        System.out.println(indent + "Bye. Hope to see you again soon! :)");
        System.out.println(indent + "____________________________________________________________");
    }
}
