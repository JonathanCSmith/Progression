package me.jonathansmith.progress.local.runtime;

import me.jonathansmith.progress.Progression;
import me.jonathansmith.progress.api.properties.ConfigurationProperties;
import me.jonathansmith.progress.api.runtime.ClientRuntime;
import me.jonathansmith.progress.common.runtime.CommonRuntime;

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

