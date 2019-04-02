package me.jonathansmith.progression.local.runtime;

import me.jonathansmith.progression.Progression;
import me.jonathansmith.progression.api.engine.Task;
import me.jonathansmith.progression.api.network.CommunicationManager;
import me.jonathansmith.progression.api.properties.ConfigurationProperties;
import me.jonathansmith.progression.api.runtime.ClientRuntime;
import me.jonathansmith.progression.common.runtime.CommonRuntime;
import me.jonathansmith.progression.common.user_interface.UserInterfaceRuntime;

import java.util.List;

public class Client extends CommonRuntime implements ClientRuntime {

    private final Progression progression;
    private final UserInterfaceRuntime userInterface;
    private final CommunicationManager communicationsManager;

    public Client(Progression progression, UserInterfaceRuntime userInterface, CommunicationManager communicationManager) {
        super();

        this.progression = progression;
        this.userInterface = userInterface;
        this.communicationsManager = communicationManager;
    }

    @Override
    public void init(ConfigurationProperties configurationProperties) {
        super.init(configurationProperties);

        this.userInterface.init();
        this.userInterface.start();

        // Handle whether we need to boot a server
    }

    @Override
    protected int runTasks() {
        this.doPreLoopTasks();
        List<Task> tasks = this.getOrderedTaskList();
        if (tasks.isEmpty()) {
            return 10;
        }

        for (Task task : tasks) {
            task.executeTask(this);
        }

        this.doPostLoopTasks();
        return 0;
    }

    @Override
    public void command(String command) {
        this.userInterface.output("Echo: " + command);
    }

    private void doPreLoopTasks() {
        List<Task> tasks = this.userInterface.gatherInputs();
        this.communicationsManager.setUpdates(tasks);
    }

    private List<Task> getOrderedTaskList() {
        return this.communicationsManager.getUpdates();
    }

    private void doPostLoopTasks() {
        // Render
        return;
    }
}
