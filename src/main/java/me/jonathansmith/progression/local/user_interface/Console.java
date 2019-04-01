package me.jonathansmith.progression.local.user_interface;

import me.jonathansmith.progression.api.engine.Task;
import me.jonathansmith.progression.api.engine.commands.CommandTask;
import me.jonathansmith.progression.api.user_interface.UserInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console implements UserInterface {

    private Scanner consoleScanner;

    public void init() {
        this.consoleScanner = new Scanner(System.in);
    }

    public void output(String itemToPrint) {
        System.out.println(itemToPrint);
    }

    public void error(String err) {
        System.err.println(err);
    }

    // TODO: I don't like handing off unnecessary lists
    public List<Task> gatherInputs() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        while (this.consoleScanner.hasNextLine()) {
            tasks.add(new CommandTask(this.consoleScanner.nextLine()));
        }

        return tasks;
    }
}
