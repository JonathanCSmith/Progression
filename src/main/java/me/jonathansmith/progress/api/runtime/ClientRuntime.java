package me.jonathansmith.api.runtime;

public interface ClientRuntime extends Runtime {

    void handleInputs();

    void updateStates();

    void render();
}
