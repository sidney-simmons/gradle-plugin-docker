package com.sidneysimmons.gradleplugindocker.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import org.gradle.api.DefaultTask;
import org.gradle.api.plugins.ExtraPropertiesExtension;
import org.gradle.api.provider.Property;

/**
 * Custom docker task type to deal with connecting to a docker machine.
 * 
 * @author Sidney Simmons
 */
public class DockerTask extends DefaultTask {

    private Property<String> machineName = getProject().getObjects().property(String.class);

    /**
     * Get the machine name.
     * 
     * @return the machine name
     */
    public Property<String> getMachineName() {
        return machineName;
    }

    /**
     * Get docker machine environment variables for this task. First checks if the machine's variables are already cached.
     * An empty map is returned if the machine name isn't configured.
     * 
     * @return the machine environment variables if configured, empty map otherwise
     * @throws IOException thrown if there are problems reading the environment variables
     * @throws InterruptedException thrown if there are problems reading the environment variables
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getDockerMachineEnvironmentVariables() throws IOException, InterruptedException {
        // Return if the machine name isn't set
        String machineNameString = machineName.getOrNull();
        if (machineNameString == null) {
            return new HashMap<>();
        }

        // Return the cached environment variables if they exist
        ExtraPropertiesExtension extraProperties = getProject().getExtensions().getExtraProperties();
        String propertyName = "dockerMachineEnvironmentVariables-" + machineNameString;
        if (extraProperties.has(propertyName)) {
            getLogger().lifecycle("Using cached docker-machine env variables for machine \"" + machineNameString + "\"...");
            return (Map<String, String>) extraProperties.get(propertyName);
        }

        // Read the environment variables from the system and cache them
        Map<String, String> environmentVariables = readDockerMachineEnvironmentVariables(machineNameString);
        extraProperties.set(propertyName, environmentVariables);
        return environmentVariables;
    }

    /**
     * Reads the docker machine environment variables from the system.
     * 
     * @param machineNameString the machine to get environment variables for
     * @return the machine environment variables
     * @throws IOException thrown if there are problems reading the environment variables
     * @throws InterruptedException thrown if there are problems reading the environment variables
     */
    private Map<String, String> readDockerMachineEnvironmentVariables(String machineNameString) throws IOException, InterruptedException {
        // Create the process
        getLogger().lifecycle("Reading docker-machine env variables for machine \"" + machineNameString + "\"...");
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.redirectErrorStream(true);
        processBuilder.command("docker-machine", "env", "--shell", "cmd", machineNameString);
        Process process = processBuilder.start();

        // Read in the result
        Map<String, String> environmentVariables = new HashMap<>();
        try (BufferedReader inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line = null;
            while ((line = inputReader.readLine()) != null) {
                line = line.trim();
                if (line.length() > 0 && line.startsWith("SET ")) {
                    String[] splitLine = line.substring("SET ".length()).split("=");
                    environmentVariables.put(splitLine[0], splitLine[1]);
                }
            }
        }

        // Wait for the process to complete
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Cannot get docker-machine environment variables. Process exited with error code: " + exitCode);
        }
        return environmentVariables;
    }

}
