package com.sidneysimmons.gradleplugindocker;

import com.sidneysimmons.gradleplugindocker.task.DockerComposeBuildTask;
import com.sidneysimmons.gradleplugindocker.task.DockerComposeDownTask;
import com.sidneysimmons.gradleplugindocker.task.DockerComposeUpTask;
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
        TaskContainer taskContainer = project.getTasks();

        // Docker version
        taskContainer.create(DockerVersionTask.NAME, DockerVersionTask.class, task -> {

        });

        // Docker compose version
        taskContainer.create(DockerComposeVersionTask.NAME, DockerComposeVersionTask.class, task -> {

        });

        // Docker compose up
        taskContainer.create(DockerComposeUpTask.NAME, DockerComposeUpTask.class, task -> {

        });

        // Docker compose down
        taskContainer.create(DockerComposeDownTask.NAME, DockerComposeDownTask.class, task -> {

        });

        // Docker compose build
        taskContainer.create(DockerComposeBuildTask.NAME, DockerComposeBuildTask.class, task -> {

        });
    }

}
