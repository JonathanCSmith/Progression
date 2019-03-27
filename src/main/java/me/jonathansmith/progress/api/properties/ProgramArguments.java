package me.jonathansmith.api.properties;

import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ProgramArguments {

    @Parameter(names = { "-config", "-configuration", "-c"}, description = "Path to a custom configuration path")
    private String configurationPath = this.getClass().getClassLoader().getResource("configuration.json").getPath();

    public ConfigurationProperties unpackConfiguration() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(configurationPath), ConfigurationProperties.class);
    }

    public String getConfigurationPath() {
        return this.configurationPath;
    }
}
