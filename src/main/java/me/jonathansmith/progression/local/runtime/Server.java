package me.jonathansmith.progression.local.runtime;

import me.jonathansmith.progression.Progression;
import me.jonathansmith.progression.api.runtime.ServerRuntime;
import me.jonathansmith.progression.common.network.CommunicationsRuntime;
import me.jonathansmith.progression.common.runtime.CommonRuntime;

public class Server extends CommonRuntime implements ServerRuntime {
    public Server(Progression progression, CommunicationsRuntime communicationsRuntime) {

    }

    @Override
    public void command(String command) {

    }

    @Override
    protected int runTasks() {
        return 0;
    }
}
