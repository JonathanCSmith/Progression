package me.jonathansmith.progression.common.runtime;

import me.jonathansmith.progression.api.engine.Task;
import me.jonathansmith.progression.api.properties.ConfigurationProperties;
import me.jonathansmith.progression.api.runtime.Runtime;

import java.util.List;

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
        super.start();
        this.hasStarted = true;
    }

    @Override
    public void run() {
        this.isRunning = true;

        while (this.isRunning) {
            int sleepDuration = this.runTasks();
            if (sleepDuration <= 0) {
                continue;
            }

            try {
                Thread.sleep(sleepDuration);
            } catch (InterruptedException e) {
                System.err.println("Error in wait thread - execution was interrupted");
                e.printStackTrace();
                this.setRunning(false);
                this.setError();
            }
        }
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

    protected abstract int runTasks();
}
