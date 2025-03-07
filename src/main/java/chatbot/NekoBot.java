package chatbot;
import chatbot.IO.Storage;
import chatbot.misc.UI;
import chatbot.IO.Parser;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;


public class NekoBot {

    public static void main(String[] args) throws UnsupportedEncodingException {
        UI.printLine();
        UI.printLogo();
        UI.println(UI.LOAD_MSG);
        Storage.loadData();
        UI.println(UI.WELCOME_MSG1);
        UI.println(UI.WELCOME_MSG2);
        UI.printLine();

        Scanner reader = new Scanner(System.in);
        String userInput = reader.nextLine().trim(); // get user input

        while (!userInput.equals(UI.END_INPUT)) {
            UI.printLine();
            Parser.parseUserInput(userInput);
            Storage.saveData();
            UI.printLine();
            UI.println(UI.CONTINUE_MSG);
            userInput = reader.nextLine().trim();
        }

        UI.println(UI.END_MSG);
        UI.printLine();
    }

}
