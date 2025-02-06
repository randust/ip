import java.util.Scanner;

public class Lava {
    public static Task[] tasks = new Task[100];

    public static void main(String[] args) {
        PrintFormat.printLine();
        PrintFormat.println("Hello I'm Lava.");
        PrintFormat.println("What can I do for you?");
        PrintFormat.printLine();

        Scanner reader = new Scanner(System.in);
        String userInput = reader.nextLine().trim(); // get user userInput

        while (!userInput.equals("bye")){
            PrintFormat.printLine();
            Parser.addTask(userInput);
            PrintFormat.printLine();
            userInput = reader.nextLine();
        }
        PrintFormat.println("Bye. Hope to see you again soon! :)");
        PrintFormat.printLine();
    }
}
