package me.jonathansmith.api.properties;

public class ConfigurationProperties {

    private int targetClientThreads;

    public int getTargetClientThreads() {
        return this.targetClientThreads;
    }

    public void setTargetClientThreads(int targetClientThreads) {
        this.targetClientThreads = targetClientThreads;
    }
}
