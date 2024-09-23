import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList =  new ArrayList<Task>();
    }

    public void addTask(Task task, Storage storage) throws IOException {
        this.taskList.add(task);
        System.out.println("Alright sir! I've added this task:");
        System.out.println(task.toString());
        storage.saveToDisk(taskList);
    }

    public void removeTask(int deleteIndex, Storage storage) throws IOException, CreateTaskException {
        if (deleteIndex < 0 || deleteIndex > this.taskList.size() - 1) {
            throw new CreateTaskException("Oh SIR! That task index does not exist!");
        }
        Task task = this.taskList.get(deleteIndex);
        this.taskList.remove(task);
        System.out.println("Unfortunate.. I'll remove this task from the list..");
        System.out.println(task.toString());
        storage.saveToDisk(taskList);
    }

    public void markTaskAsDone(int markIndex, Storage storage) throws IOException, CreateTaskException {
        if (markIndex < 0 || markIndex > this.taskList.size() - 1) {
            throw new CreateTaskException("Oh SIR! That task index does not exist!");
        }
        taskList.get(markIndex).markAsDone();
        storage.saveToDisk(taskList);
    }

    public void markTaskAsNotDone(int unmarkIndex, Storage storage) throws IOException, CreateTaskException {
        if (unmarkIndex < 0 || unmarkIndex > this.taskList.size() - 1) {
            throw new CreateTaskException("Oh SIR! That task index does not exist!");
        }
        taskList.get(unmarkIndex).markAsNotDone();
        storage.saveToDisk(taskList);
    }
    
    public void listTasks() {
        System.out.println("As requested, here are your outstanding tasks sir:");
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println(i + 1 + "." + this.taskList.get(i).toString());
        }
    }

}
