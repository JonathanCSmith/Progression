package me.jonathansmith.progression.api.network;

import me.jonathansmith.progression.api.engine.Task;
import me.jonathansmith.progression.api.runtime.ClientRuntime;
import me.jonathansmith.progression.api.runtime.ServerRuntime;

import java.util.List;

public interface CommunicationManager {

    void bindEndPoints(ClientRuntime clientRuntime, ServerRuntime serverRuntime);

    List<Task> getUpdates();

    void setUpdates(List<Task> tasks);
}
