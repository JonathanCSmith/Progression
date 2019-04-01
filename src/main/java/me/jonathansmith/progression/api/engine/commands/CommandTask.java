package me.jonathansmith.progression.api.engine.commands;

import me.jonathansmith.progression.api.engine.Task;

public class CommandTask implements Task {
    private String command;

    public CommandTask(String command) {
        this.command = command;
    }
}
