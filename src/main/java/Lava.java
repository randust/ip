import java.util.Scanner;
public class Lava {
    public static void main(String[] args) {
        String indent = "    ";
        System.out.println(indent+"____________________________________________________________");
        System.out.println(indent+"Hello I'm Lava.");
        System.out.println(indent+"What can I do for you?");
        System.out.println(indent+"____________________________________________________________");
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        while (!input.equals("bye")){
            System.out.println(indent+"____________________________________________________________");
            System.out.println(indent+input);
            System.out.println(indent+"____________________________________________________________");
            input = reader.nextLine();
        }
        System.out.println(indent+"Bye. Hope to see you again soon!");
        System.out.println(indent+"____________________________________________________________");
    }
}
