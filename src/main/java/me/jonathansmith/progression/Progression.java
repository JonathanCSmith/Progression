package me.jonathansmith.progression;

import java.io.IOException;
import java.util.MissingResourceException;

import com.beust.jcommander.JCommander;

import me.jonathansmith.progression.api.properties.ConfigurationProperties;
import me.jonathansmith.progression.api.properties.ProgramArguments;
import me.jonathansmith.progression.common.network.CommunicationsRuntime;
import me.jonathansmith.progression.local.runtime.Client;
import me.jonathansmith.progression.local.runtime.Server;
import me.jonathansmith.progression.local.user_interface.ClientWindow;
import me.jonathansmith.progression.local.user_interface.Console;
import me.jonathansmith.progression.local.user_interface.ConsoleWindow;
import me.jonathansmith.progression.local.user_interface.ServerWindow;


public class Progression {

    // Main entry point for Progression
    public static void main(String[] args) {
        System.out.println("Processing Arguments");
        ProgramArguments arguments = new ProgramArguments();
        JCommander.newBuilder().addObject(arguments).build().parse(args);

        // Construct the basic environment
        System.out.println("Creating the progression application.");
        Progression progression = new Progression(arguments);

        // Initialise the runtimes
        System.out.println("Initialising the Progression Application");
        try {
            progression.buildRuntime();
        }

        catch (IOException e) {
            System.err.println("Could not initialise the tree of life application - likely due to a missing resource.");
            e.printStackTrace();
            System.exit(0);
        }

        progression.run();
    }

    private final ProgramArguments arguments;

    private ConfigurationProperties configurationProperties = null;

    private CommunicationsRuntime communicationsRuntime;
    private Client clientRuntime;
    private Server serverRuntime;

    private Progression(ProgramArguments arguments) {
        this.arguments = arguments;
    }

    // Used to initialise the basic program properties
    private void buildRuntime() throws IOException, MissingResourceException {
        this.configurationProperties = this.arguments.unpackConfiguration();
        if (this.configurationProperties == null) {
            throw new MissingResourceException("Could not load the configuration file.", this.getClass().toString(), this.arguments.getConfigurationPath());
        }

        // Validate our runtime states
        if (!this.configurationProperties.validateRuntimeState()) {
            System.err.println("Could not validate the provided runtime states in accordance with the execution environment");
            System.exit(0);
        }

        // Build our communication runtime
        this.communicationsRuntime = new CommunicationsRuntime();

        // Detect our runtime environment and switch out for now default to locals
        /*
            Runtime types (UX):

                0) REPL Like
                1) Custom window for REPL
                2) GUI for Server
                3) GUI for Client

         */
        switch (this.configurationProperties.getInterfaceType()) {
            case CONSOLE:
                this.clientRuntime = new Client(this, new Console(), this.communicationsRuntime);
                break;

//            case CONSOLE_WINDOW:
//                this.clientRuntime = new Client(this, new ConsoleWindow(), this.communicationsRuntime);
//                break;
//
//            case SERVER:
//                this.clientRuntime = new Client(this, new ServerWindow(), this.communicationsRuntime);
//                break;
//
//            case CLIENT:
//                this.clientRuntime = new Client(this, new ClientWindow(), this.communicationsRuntime);
//                break;
        }
        this.serverRuntime = new Server(this, this.communicationsRuntime);

        // Bind our endpoints
        this.communicationsRuntime.bindEndPoints(this.clientRuntime, this.serverRuntime);
    }

    private void run() {
        // Fail fast
        if (this.clientRuntime == null) {
            return;
        }

        // Initialise all of the runtimes
        this.communicationsRuntime.init(this.configurationProperties);
        this.serverRuntime.init(this.configurationProperties);
        this.clientRuntime.init(this.configurationProperties);

        // Start the thread
        this.communicationsRuntime.start();
        this.serverRuntime.start();
        this.clientRuntime.start();

        // Wait for the program to start running
        while (!this.communicationsRuntime.isRunning() || !this.serverRuntime.isRunning() || !this.clientRuntime.isRunning()) {
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
}