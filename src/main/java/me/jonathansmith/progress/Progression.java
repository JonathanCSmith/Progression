package me.jonathansmith.progress;

import me.jonathansmith.progress.api.properties.ConfigurationProperties;
import me.jonathansmith.progress.api.properties.ProgramArguments;
import me.jonathansmith.progress.api.runtime.ClientRuntime;
import me.jonathansmith.progress.api.runtime.ServerRuntime;
import com.beust.jcommander.JCommander;
import me.jonathansmith.progress.local.runtime.LocalClientConsoleRuntime;

import java.io.IOException;
import java.util.MissingResourceException;

public class Progression {

    private final ProgramArguments arguments;

    private ConfigurationProperties configurationProperties = null;

    private ClientRuntime clientRuntime;
    private ServerRuntime serverRuntime;

    private Progression(ProgramArguments arguments) {
        this.arguments = arguments;
    }

    // Used to initialise the basic program properties
    private void buildRuntimes() throws IOException, MissingResourceException {
        this.configurationProperties = this.arguments.unpackConfiguration();
        if (this.configurationProperties == null) {
            throw new MissingResourceException("Could not load the configuration file.", this.getClass().toString(), this.arguments.getConfigurationPath());
        }

        // TODO: Detect our runtime environment and switch out for now default to locals
        this.clientRuntime = new LocalClientConsoleRuntime(this);
    }

    private void run() {
        // Fail fast
        if (this.clientRuntime == null) {
            return;
        }

        // Initialise the client runtime
        this.clientRuntime.init(this.configurationProperties);

        // Start the thread
        this.clientRuntime.start();

        // Wait for the program to start
        while (!this.clientRuntime.hasStarted()) {
            try {
                Thread.sleep(10);
            }

            catch (InterruptedException ex) {
                // TODO: Exception handling
            }
        }

        // Just join the thread
        try {
            this.clientRuntime.inline();
        }

        catch (InterruptedException ex) {
            // TODO: Exception handling
        }

        // Error handling
    }

    // Main entry point for ToL
    public static void main(String[] args) {
        System.out.println("Processing Arguments");
        ProgramArguments arguments = new ProgramArguments();
        JCommander.newBuilder().addObject(arguments).build().parse(args);

        // Construct the basic environment
        System.out.println("Creating the tree of life application.");
        Progression progression = new Progression(arguments);

        // Initialise the runtimes
        System.out.println("Initialising the Tree of Life Application");
        try {
            progression.buildRuntimes();
        }

        catch (IOException e) {
            System.out.println("Could not initialise the tree of life application - likely due to a missing resource.");
            e.printStackTrace();
            System.exit(0);
        }

        progression.run();
    }
}