package com.sidneysimmons.gradleplugindocker.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.gradle.api.DefaultTask;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException;

/**
 * Task used to print the system's "docker" version.
 * 
 * @author Sidney Simmons
 */
public class DockerVersionTask extends DefaultTask {

	private Logger logger = Logging.getLogger(getClass());

	public static final String GROUP = "docker";
	public static final String NAME = "dockerVersion";
	public static final String DESCRIPTION = "Prints system docker version.";

	@TaskAction
	void action() throws IOException, InterruptedException {
		// Start the process
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.redirectErrorStream(true);
		processBuilder.command("docker", "--version");
		Process process = processBuilder.start();

		// Read in the result
		try (BufferedReader inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
			String line = null;
			while ((line = inputReader.readLine()) != null) {
				logger.lifecycle(line);
			}
		}

		// Wait for the process to finish
		int exitCode = process.waitFor();
		if (exitCode != 0) {
			throw new TaskExecutionException(this, new RuntimeException("Task exited with error code: " + exitCode));
		}
	}

}