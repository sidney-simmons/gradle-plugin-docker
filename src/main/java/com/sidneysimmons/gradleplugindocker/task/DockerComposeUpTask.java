package com.sidneysimmons.gradleplugindocker.task;

import com.sidneysimmons.gradleplugindocker.util.PluginUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.gradle.api.provider.MapProperty;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Internal;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException;

/**
 * Task used to "up" a "docker-compose" file.
 * 
 * @author Sidney Simmons
 */
public class DockerComposeUpTask extends DockerTask {

    public static final String GROUP = "docker";
    public static final String NAME = "dockerComposeUp";
    public static final String DESCRIPTION = "Run docker-compose up.";

    @Input
    @Optional
    private MapProperty<String, Integer> servicesToScale = getProject().getObjects().mapProperty(String.class,
            Integer.class);

    @TaskAction
    void action() throws IOException, InterruptedException {
        // Start the process
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.redirectErrorStream(true);
        processBuilder.environment().putAll(getEnvironmentVariables());
        processBuilder.command("docker-compose", "--no-ansi", "up", "--detach");
        processBuilder.command().addAll(buildServiceScalingOption());
        Process process = processBuilder.start();

        // Read in the result
        PluginUtil.logProcessOutput(process, getLogger());

        // Wait for the process to finish
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new TaskExecutionException(this, new RuntimeException("Task exited with error code: " + exitCode));
        }
    }

    /**
     * Build the command line option for telling docker to scale specific services.
     * 
     * @return a list of command line arguments
     */
    private List<String> buildServiceScalingOption() {
        List<String> scalingOption = new ArrayList<>();
        Map<String, Integer> serviceMap = servicesToScale.getOrElse(new HashMap<>());
        for (Entry<String, Integer> entry : serviceMap.entrySet()) {
            scalingOption.add("--scale");
            scalingOption.add(entry.getKey() + "=" + entry.getValue());
        }
        return scalingOption;
    }

    /**
     * Get the services to scale.
     * 
     * @return the services to scale
     */
    public MapProperty<String, Integer> getServicesToScale() {
        return servicesToScale;
    }

    @Override
    @Internal
    public String getGroup() {
        return GROUP;
    }

    @Override
    @Internal
    public String getDescription() {
        return DESCRIPTION;
    }

}