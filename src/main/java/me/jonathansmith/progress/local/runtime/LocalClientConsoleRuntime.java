package me.jonathansmith.local.runtime;

import me.jonathansmith.TreeOfLife;
import me.jonathansmith.api.properties.ConfigurationProperties;
import me.jonathansmith.api.runtime.ClientRuntime;
import me.jonathansmith.common.runtime.CommonRuntime;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

public class LocalClientConsoleRuntime extends CommonRuntime implements ClientRuntime {

    private final TreeOfLife treeOfLife;

    private long window;

    private int xWidth = 1200;
    private int yHeight = 800;
    private int initialXPosition = 100;
    private int initialYPosition = 100;

    public LocalClientConsoleRuntime(TreeOfLife treeOfLife) {
        this.treeOfLife = treeOfLife;
    }

    public void init(ConfigurationProperties configurationProperties) {
        super.init(configurationProperties);

        // Initialise our game window
        if (!GLFW.glfwInit()) {
            // TODO: Log
            System.err.println("GLFW initialization failed!");
            return;
        }

        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GL11.GL_TRUE);
        this.window = GLFW.glfwCreateWindow(this.xWidth, this.yHeight, "Endless Runner", MemoryUtil.NULL, MemoryUtil.NULL);

        if (this.window == MemoryUtil.NULL) {
            // TODO: Log
            System.err.println("Could not create the window!");
            return;
        }

        GLFWVidMode vidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(this.window, this.initialXPosition, this.initialYPosition);
        GLFW.glfwMakeContextCurrent(this.window);
        GLFW.glfwShowWindow(this.window);
    }

    public void start() {
        super.start();
        this.setStarted();
    }

    @Override
    public void run() {
        while (this.isRunning() && !this.hasError()) {
            this.handleInputs();
            this.updateStates();
            this.render();

            if (GLFW.glfwWindowShouldClose(this.window)) {
                this.setRunning(false);
            }
        }
    }

    public void handleInputs() {

    }

    public void updateStates() {
        GLFW.glfwPollEvents();
    }

    public void render() {
        GLFW.glfwSwapBuffers(this.window);
    }
}
