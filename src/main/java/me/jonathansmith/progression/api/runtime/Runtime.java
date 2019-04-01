package me.jonathansmith.progression.api.runtime;

import me.jonathansmith.progression.api.properties.ConfigurationProperties;

public interface Runtime extends Runnable {

    void init(ConfigurationProperties configurationProperties);

    void start();

    void inline() throws InterruptedException;

    boolean hasStarted();

    boolean isRunning();

    boolean hasError();
}