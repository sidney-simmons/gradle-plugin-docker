package com.sidneysimmons.gradleplugindocker;

import com.sidneysimmons.gradleplugindocker.task.DockerComposeVersionTask;
import com.sidneysimmons.gradleplugindocker.task.DockerVersionTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskContainer;

/**
 * Custom plugin for integrating with Docker.
 * 
 * @author Sidney Simmons
 */
public class DockerPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		// Docker version
		TaskContainer taskContainer = project.getTasks();
		taskContainer.create(DockerVersionTask.NAME, DockerVersionTask.class, task -> {
			task.setGroup(DockerVersionTask.GROUP);
			task.setDescription(DockerVersionTask.DESCRIPTION);
		});

		// Docker compose version
		taskContainer.create(DockerComposeVersionTask.NAME, DockerComposeVersionTask.class, task -> {
			task.setGroup(DockerComposeVersionTask.GROUP);
			task.setDescription(DockerComposeVersionTask.DESCRIPTION);
		});
	}

}
