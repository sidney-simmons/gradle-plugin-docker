package com.sidneysimmons.gradleplugindocker.extension;

import org.gradle.api.Project;
import org.gradle.api.provider.Property;

/**
 * Plugin extension for docker related configuration.
 * 
 * @author Sidney Simmons
 */
public class DockerExtension {

    public static final String NAME = "docker";

    private Property<String> machineName;

    /**
     * Constructor to initialize the properties.
     * 
     * @param project the project
     */
    public DockerExtension(Project project) {
        machineName = project.getObjects().property(String.class);
    }

    /**
     * Get the machine name.
     * 
     * @return the machine name
     */
    public Property<String> getMachineName() {
        return machineName;
    }

}