package me.jonathansmith.progression.api.user_interface;

import me.jonathansmith.progression.api.engine.Task;

import java.util.List;

public interface UserInterface {
    void init();

    List<Task> gatherInputs();
}
