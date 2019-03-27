package me.jonathansmith.progress.common.runtime;

import me.jonathansmith.progress.api.properties.ConfigurationProperties;
import me.jonathansmith.progress.api.runtime.Runtime;

/**
 * Common runtime thread properties
 */
public abstract class CommonRuntime extends Thread implements Runtime {

    protected ConfigurationProperties configurationProperties;

    private boolean hasStarted = false;
    private boolean isRunning = false;
    private boolean hasError = false;

    public void init(ConfigurationProperties configurationProperties) {
        this.configurationProperties = configurationProperties;
    }

    @Override
    public void start() {
        this.hasStarted = true;
    }

    @Override
    public void run() {

    }

    public final void inline() throws InterruptedException {
        this.join();
    }

    public boolean hasStarted() {
        return this.hasStarted;
    }

    protected void setStarted() {
        this.hasStarted = true;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    protected void setRunning(boolean runningState) {
        this.isRunning = runningState;
    }

    public boolean hasError() {
        return this.hasError;
    }

    protected void setError() {
        this.hasError = true;
    }
}
