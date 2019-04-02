package me.jonathansmith.progression.common.network;

import me.jonathansmith.progression.api.engine.Task;
import me.jonathansmith.progression.api.network.CommunicationManager;
import me.jonathansmith.progression.api.runtime.ClientRuntime;
import me.jonathansmith.progression.api.runtime.ServerRuntime;
import me.jonathansmith.progression.common.runtime.CommonRuntime;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CommunicationsRuntime extends CommonRuntime implements CommunicationManager {

    private final Queue<Task> tasksQueue = new ConcurrentLinkedQueue<Task>();

    @Override
    public void bindEndPoints(ClientRuntime clientRuntime, ServerRuntime serverRuntime) {

    }

    @Override
    public List<Task> getUpdates() {
        List<Task> tasks = new ArrayList<Task>();
        while (this.tasksQueue.peek() != null) {
            tasks.add(this.tasksQueue.poll());
        }

        return tasks;
    }

    @Override
    public void setUpdates(List<Task> tasks) {
        this.tasksQueue.addAll(tasks);
    }

    @Override
    protected int runTasks() {
        return 10;
    }
}
