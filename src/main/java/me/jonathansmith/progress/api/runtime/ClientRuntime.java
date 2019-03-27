package me.jonathansmith.progress.api.runtime;

public interface ClientRuntime extends Runtime {

    void handleInputs();

    void updateStates();

    void render();
}
