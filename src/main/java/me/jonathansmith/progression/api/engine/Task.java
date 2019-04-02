package me.jonathansmith.progression.api.engine;

import me.jonathansmith.progression.api.runtime.GameRuntime;
import me.jonathansmith.progression.common.runtime.CommonRuntime;

public interface Task {

    void executeTask(GameRuntime commonRuntime);
}
