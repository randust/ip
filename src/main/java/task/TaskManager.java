package task;
import java.util.ArrayList;
import java.util.List;
public class TaskManager {
    private static List<Task> tasks = new ArrayList<>();

    public static Task getTask(int index){
        return tasks.get(index);
    }

    public static int getLength(){
        return tasks.size();
    }

    public static void addTask(Task task){
        tasks.add(task);
    }
}
