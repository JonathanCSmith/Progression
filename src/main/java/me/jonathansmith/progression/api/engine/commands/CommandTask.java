package me.jonathansmith.progression.api.engine.commands;

import me.jonathansmith.progression.api.engine.Task;
import me.jonathansmith.progression.api.runtime.GameRuntime;

public class CommandTask implements Task {
    private String command;

    public CommandTask(String command) {
        this.command = command;
    }

    @Override
    public void executeTask(GameRuntime commonRuntime) {
        commonRuntime.command(this.command);
    }
}
