public class PrintFormat {
    public static final String INDENT = "    ";
    public static final String SEPARATE_LINE = "____________________________________________________________";


    public static void println(Object obj) {
        System.out.println(INDENT + obj);
    }
    public static void printf(Object obj) {
        System.out.printf(INDENT + obj);
    }
    public static void printLine(){
        System.out.println(INDENT + SEPARATE_LINE);
    }
}
