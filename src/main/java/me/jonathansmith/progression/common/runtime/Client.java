package me.jonathansmith.progression.common.runtime;

import me.jonathansmith.progression.Progression;
import me.jonathansmith.progression.api.engine.Task;
import me.jonathansmith.progression.api.properties.ConfigurationProperties;
import me.jonathansmith.progression.api.runtime.ClientRuntime;
import me.jonathansmith.progression.api.user_interface.UserInterface;

import java.util.List;

public class Client extends CommonRuntime implements ClientRuntime {

    private final Progression progression;
    private final UserInterface userInterface;

    public Client(Progression progression, UserInterface userInterface) {
        super();

        this.progression = progression;
        this.userInterface = userInterface;

        // Build network manager
        // Build server
    }

    @Override
    public void init(ConfigurationProperties configurationProperties) {
        super.init(configurationProperties);

        this.userInterface.init();

        // Handle whether we need to boot a server
    }

    public void handleInputs() {
        List<Task> tasks = this.userInterface.gatherInputs();
        this.communicationsManager.processUpdates(tasks);
    }

    public void updateStates() {
        List<Task> tasks = this.communicationsManager.gatherUpdates();

        // Process updates
    }

    public void render() {

        // Render
    }
}
