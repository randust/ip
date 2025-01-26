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
        String[] storage = new String[100];
        int lastEle = 0;
        while (!input.equals("bye")){
            System.out.println(indent+"____________________________________________________________");
            if (!input.equals("list")){
                storage[lastEle++] = input;
                System.out.println(indent+"added: "+input);
            }else{
                for (int i = 0; i < lastEle; i++){
                    System.out.println(indent+(i+1)+". "+storage[i]);
                }
            }
            System.out.println(indent+"____________________________________________________________");
            input = reader.nextLine();
        }
        System.out.println(indent+"Bye. Hope to see you again soon!");
        System.out.println(indent+"____________________________________________________________");
    }
}
