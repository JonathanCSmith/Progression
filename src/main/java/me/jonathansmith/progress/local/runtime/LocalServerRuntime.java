package me.jonathansmith.progress.local.runtime;

import me.jonathansmith.progress.api.properties.ConfigurationProperties;
import me.jonathansmith.progress.api.runtime.ServerRuntime;

public class LocalServerRuntime implements ServerRuntime {

    private int availableThreads = 1;

    public void init(ConfigurationProperties configurationProperties) {
        this.availableThreads = Runtime.getRuntime().availableProcessors() - configurationProperties.getTargetClientThreads();
    }

    public void start() {

    }

    public void inline() throws InterruptedException {

    }

    public boolean hasStarted() {
        return false;
    }

    public boolean isRunning() {
        return false;
    }

    public boolean hasError() {
        return false;
    }

    public void run() {

    }
}
