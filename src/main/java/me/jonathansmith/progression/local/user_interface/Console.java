package me.jonathansmith.progression.local.user_interface;

import me.jonathansmith.progression.api.engine.Task;
import me.jonathansmith.progression.api.engine.commands.CommandTask;
import me.jonathansmith.progression.common.user_interface.UserInterfaceRuntime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Console extends UserInterfaceRuntime {

    private final ConcurrentLinkedQueue<Task> tasksQueue = new ConcurrentLinkedQueue<Task>();

    private Scanner consoleScanner;

    public void init() {
        this.consoleScanner = new Scanner(System.in);
    }

    @Override
    public void output(String itemToPrint) {
        System.out.println(itemToPrint);
    }

    @Override
    public void error(String err) {
        System.err.println(err);
    }

    @Override
    public List<Task> gatherInputs() {
        List<Task> tasks = new ArrayList<Task>();
        while (this.tasksQueue.peek() != null) {
            tasks.add(this.tasksQueue.poll());
        }

        return tasks;
    }

    @Override
    protected int runTasks() {
        while (this.consoleScanner.hasNextLine()) {
            this.output("Adding a line");
            this.tasksQueue.add(new CommandTask(this.consoleScanner.nextLine()));
        }

        return 0;
    }
}
