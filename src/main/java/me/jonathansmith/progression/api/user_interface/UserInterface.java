package me.jonathansmith.progression.api.user_interface;

import me.jonathansmith.progression.api.engine.Task;

import java.util.List;

public interface UserInterface {
    void init();

    void output(String itemToPrint);

    void error(String err);

    List<Task> gatherInputs();
}
