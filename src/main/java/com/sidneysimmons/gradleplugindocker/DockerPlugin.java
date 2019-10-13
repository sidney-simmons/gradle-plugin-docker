package com.sidneysimmons.gradleplugindocker;

import com.sidneysimmons.gradleplugindocker.extension.DockerExtension;
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
        DockerExtension dockerExtension = project.getExtensions().create(DockerExtension.NAME, DockerExtension.class,
                project);
        TaskContainer taskContainer = project.getTasks();

        // Version tasks
        taskContainer.register(DockerVersionTask.NAME, DockerVersionTask.class, task -> {

        });
        taskContainer.register(DockerComposeVersionTask.NAME, DockerComposeVersionTask.class, task -> {

        });

        // Docker compose up
        taskContainer.register(DockerComposeUpTask.NAME, DockerComposeUpTask.class, task -> {
            task.getMachineName().set(dockerExtension.getMachineName());
            task.getExtraEnvironmentVariables().set(dockerExtension.getExtraEnvironmentVariables());
            task.getServicesToScale().set(dockerExtension.getServicesToScale());
        });

        // Docker compose down
        taskContainer.register(DockerComposeDownTask.NAME, DockerComposeDownTask.class, task -> {
            task.getMachineName().set(dockerExtension.getMachineName());
            task.getExtraEnvironmentVariables().set(dockerExtension.getExtraEnvironmentVariables());
        });

        // Docker compose build
        taskContainer.register(DockerComposeBuildTask.NAME, DockerComposeBuildTask.class, task -> {
            task.getMachineName().set(dockerExtension.getMachineName());
            task.getExtraEnvironmentVariables().set(dockerExtension.getExtraEnvironmentVariables());
        });
    }

}
