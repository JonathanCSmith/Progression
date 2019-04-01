package me.jonathansmith.progression.local.runtime;

import me.jonathansmith.progression.Progression;
import me.jonathansmith.progression.api.properties.ConfigurationProperties;
import me.jonathansmith.progression.api.runtime.ClientRuntime;
import me.jonathansmith.progression.common.runtime.CommonRuntime;

public class LocalClientGUIRuntime extends CommonRuntime implements ClientRuntime {

    private final Progression applicationWrapper;

    private int availableThreads = 1;

    public LocalClientGUIRuntime(Progression progression) {
        this.applicationWrapper = progression;
    }

    public void init(ConfigurationProperties configurationProperties) {
        this.availableThreads = configurationProperties.getTargetClientThreads();
    }

    public void handleInputs() {

    }

    public void updateStates() {

    }

    public void render() {

    }
}

