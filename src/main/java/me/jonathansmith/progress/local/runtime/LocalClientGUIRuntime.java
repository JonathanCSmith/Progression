package me.jonathansmith.local.runtime;

import me.jonathansmith.TreeOfLife;
import me.jonathansmith.api.properties.ConfigurationProperties;
import me.jonathansmith.api.runtime.ClientRuntime;
import me.jonathansmith.common.runtime.CommonRuntime;

public class LocalClientGUIRuntime extends CommonRuntime implements ClientRuntime {

    private final TreeOfLife applicationWrapper;

    private int availableThreads = 1;

    public LocalClientGUIRuntime(TreeOfLife treeOfLife) {
        this.applicationWrapper = treeOfLife;
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

