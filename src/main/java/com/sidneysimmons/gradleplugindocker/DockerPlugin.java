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

        // Docker compose up
        taskContainer.create(DockerComposeUpTask.NAME, DockerComposeUpTask.class, task -> {
            task.setGroup(DockerComposeUpTask.GROUP);
            task.setDescription(DockerComposeUpTask.DESCRIPTION);
        });

        // Docker compose down
        taskContainer.create(DockerComposeDownTask.NAME, DockerComposeDownTask.class, task -> {
            task.setGroup(DockerComposeDownTask.GROUP);
            task.setDescription(DockerComposeDownTask.DESCRIPTION);
        });

        // Docker compose build
        taskContainer.create(DockerComposeBuildTask.NAME, DockerComposeBuildTask.class, task -> {
            task.setGroup(DockerComposeBuildTask.GROUP);
            task.setDescription(DockerComposeBuildTask.DESCRIPTION);
        });
    }

}
