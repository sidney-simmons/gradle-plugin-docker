package com.sidneysimmons.gradleplugindocker.task;

import com.sidneysimmons.gradleplugindocker.util.PluginUtil;
import java.io.IOException;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException;

/**
 * Task used to "build" a "docker-compose" file.
 * 
 * @author Sidney Simmons
 */
public class DockerComposeBuildTask extends DockerTask {

    public static final String GROUP = "docker";
    public static final String NAME = "dockerComposeBuild";
    public static final String DESCRIPTION = "Run docker-compose build.";

    @TaskAction
    void action() throws IOException, InterruptedException {
        // Start the process
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.redirectErrorStream(true);
        processBuilder.environment().putAll(getDockerMachineEnvironmentVariables());
        processBuilder.command("docker-compose", "--no-ansi", "build");
        Process process = processBuilder.start();

        // Read in the result
        PluginUtil.logProcessOutput(process, getLogger());

        // Wait for the process to finish
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new TaskExecutionException(this, new RuntimeException("Task exited with error code: " + exitCode));
        }
    }

    @Override
    public String getGroup() {
        return GROUP;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

}