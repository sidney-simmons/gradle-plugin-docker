package com.sidneysimmons.gradleplugindocker.extension;

import java.util.HashMap;
import java.util.Map;
import org.gradle.api.Project;
import org.gradle.api.provider.MapProperty;
import org.gradle.api.provider.Property;

/**
 * Plugin extension for docker related configuration.
 * 
 * @author Sidney Simmons
 */
public class DockerExtension {

    public static final String NAME = "docker";
    private Property<String> machineName;
    private MapProperty<String, String> extraEnvironmentVariables;

    /**
     * Constructor to initialize the properties.
     * 
     * @param project the project
     */
    public DockerExtension(Project project) {
        machineName = project.getObjects().property(String.class);

        // Default the map of extra environment variables to an empty map
        extraEnvironmentVariables = project.getObjects().mapProperty(String.class, String.class);
        extraEnvironmentVariables.set(new HashMap<>());
    }

    /**
     * Add a key/value pair to the map of extra environment variables.
     * 
     * @param key the key
     * @param value the value
     */
    public void environment(String key, String value) {
        Map<String, String> existingExtraEnvironmentVariables = extraEnvironmentVariables.get();
        Map<String, String> newExtraEnvironmentVariables = new HashMap<>(existingExtraEnvironmentVariables);
        newExtraEnvironmentVariables.put(key, value);
        extraEnvironmentVariables.set(newExtraEnvironmentVariables);
    }

    /**
     * Get the machine name.
     * 
     * @return the machine name
     */
    public Property<String> getMachineName() {
        return machineName;
    }

    /**
     * Get the extra environment variables.
     * 
     * @return the extra environment variables
     */
    public MapProperty<String, String> getExtraEnvironmentVariables() {
        return extraEnvironmentVariables;
    }

}