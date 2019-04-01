package me.jonathansmith.progression.api.properties;

import me.jonathansmith.progression.api.user_interface.InterfaceType;

public class ConfigurationProperties {

    private int targetClientThreads;

    public int getTargetClientThreads() {
        return this.targetClientThreads;
    }

    public void setTargetClientThreads(int targetClientThreads) {
        this.targetClientThreads = targetClientThreads;
    }

    public InterfaceType getInterfaceType() {
        return InterfaceType.CONSOLE;
    }

    public boolean validateRuntimeState() {
        return false;
    }
}
