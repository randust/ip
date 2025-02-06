import java.util.Scanner;

public class NekoBot {
    public static Task[] tasks = new Task[100];

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
            Parser.addTask(userInput);
            PrintFormat.printLine();
            PrintFormat.println("Meow~ Anything else, nya? (＾• ω •＾)");
            userInput = reader.nextLine();
        }

        PrintFormat.println("Nyaa~ Bye bye! Hope to see you again soon! (ฅ^•ﻌ•^ฅ)");
        PrintFormat.printLine();
    }
}
